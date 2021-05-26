package com.ntnn.verticle;

import com.ntnn.balance.ApiBalanceGrpc;
import com.ntnn.grpc.balance.Request;
import com.ntnn.grpc.balance.Response;
import com.ntnn.utils.dao.BalanceDAO;
import com.ntnn.utils.jwt.JwtUtils;
import com.ntnn.utils.model.User;
import com.ntnn.utils.utils.BackendError;
import com.ntnn.utils.utils.ISOFieldUtils;
import com.ntnn.utils.utils.ISOMsgUtils;
import io.grpc.stub.StreamObserver;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j2;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

@Log4j2
public class ServiceImpl extends ApiBalanceGrpc.ApiBalanceImplBase {

    public Vertx vertx;
    public String name;

    public ServiceImpl(Vertx vertx, String name) {
        this.vertx = vertx;
        this.name =  name;
    }

    @Override
    public void withdrawal(Request request, StreamObserver<Response> responseObserver) {
        String data = request.getData();
        Response response;
        if (data == null || data.equals("")) {
            response = Response.newBuilder()
                    .setErrorCode(BackendError.NOT_FOUND)
                    .setResult(false)
                    .setMessage("Data is null or blank")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        ISOMsg isoMsg = ISOMsgUtils.unpack(data);
        JsonObject jo = ISOMsgUtils.parseToJson(isoMsg);
        try {
            if (!isoMsg.getMTI().equals(ISOFieldUtils.MTI_WITHDRAWAL_REQ)) {
                response = Response.newBuilder()
                        .setErrorCode(BackendError.NOT_FOUND)
                        .setResult(false)
                        .setMessage("Yor request ISOMessage not match")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
        } catch (ISOException ex) {
            log.error(ex.getMessage());
        }
        String token = jo.getString(String.valueOf(ISOFieldUtils.TOKEN));
        if (token == null || token.isEmpty()) {
            response = Response.newBuilder()
                    .setErrorCode(BackendError.TOKEN_NOT_FOUND)
                    .setResult(false)
                    .setMessage("Yor token is empty")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        JsonObject authInfo = new JsonObject()
                .put("jwt", token);
        JwtUtils.verify(vertx, authInfo, whenDone -> {
            if(whenDone == null) {
                Response responseVerify = Response.newBuilder()
                        .setErrorCode(BackendError.TOKEN_NOT_FOUND)
                        .setResult(false)
                        .setMessage("Yor token is no valid")
                        .build();
                responseObserver.onNext(responseVerify);
                responseObserver.onCompleted();
                return;
            }
            JsonObject userJwt = whenDone.principal();
            String cardNumber = userJwt.getString("cardNumber");
            if(cardNumber == null || cardNumber.isEmpty()) {
                Response responseVerify = Response.newBuilder()
                        .setErrorCode(BackendError.TOKEN_NOT_FOUND)
                        .setResult(false)
                        .setMessage("Yor token no contain cardNumber")
                        .build();
                responseObserver.onNext(responseVerify);
                responseObserver.onCompleted();
                return;
            }
            User user = User.builder()
                    .cardNumber(cardNumber)
                    .build();
            BalanceDAO balanceDAO = new BalanceDAO(vertx, "BalanceDAO");
            balanceDAO.selectByCardNumber(userJwt, asyncResult -> {
                if(!asyncResult.getBoolean("result")) {
                    Response responseVerify = Response.newBuilder()
                            .setErrorCode(BackendError.NOT_FOUND)
                            .setResult(false)
                            .setMessage("Can't find balance")
                            .build();
                    responseObserver.onNext(responseVerify);
                    responseObserver.onCompleted();
                    return;
                }
                JsonObject balanceObj = asyncResult.getJsonObject("data");
                Double currentAmount = balanceObj.getDouble("currentAmount");
                Double moneyWithdrawal = Double.parseDouble(jo.getString(String.valueOf(ISOFieldUtils.AMOUNT)));
                if (currentAmount < moneyWithdrawal) {
                    Response responseVerify = Response.newBuilder()
                            .setErrorCode(BackendError.MONEY_NOT_ENOUGH)
                            .setResult(false)
                            .setMessage("Your money not enough")
                            .build();
                    responseObserver.onNext(responseVerify);
                    responseObserver.onCompleted();
                    return;
                }
                balanceObj.put("currentAmount", currentAmount - moneyWithdrawal);
                balanceObj.put("lastAmount", currentAmount);
                balanceDAO.update(balanceObj, asyncUpdate -> {
                    if(!asyncUpdate.getBoolean("result")) {
                        Response responseVerify = Response.newBuilder()
                                .setErrorCode(BackendError.SYSTEM_ERR)
                                .setResult(false)
                                .setMessage("Can't update balance")
                                .build();
                        responseObserver.onNext(responseVerify);
                        responseObserver.onCompleted();
                        return;
                    }
                    JsonObject dataResponse = new JsonObject()
                            .put("errorCode", BackendError.SUCCESS)
                            .put("message", "Withdrawal success")
                            .put("status", true)
                            .put("data", balanceObj.toString());
                    Response res = Response.newBuilder()
                            .setResult(true)
                            .setErrorCode(BackendError.SUCCESS)
                            .setMessage("Withdrawal success")
                            .setData(dataResponse.toString())
                            .build();
                    responseObserver.onNext(res);
                    responseObserver.onCompleted();
                    return;
                });
            });
        });
    }
}

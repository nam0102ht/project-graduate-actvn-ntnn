package com.ntnn.main;

import com.ntnn.grpc.atm.ApiBalanceGrpc;
import com.ntnn.grpc.atm.Request;
import com.ntnn.grpc.atm.Response;
import io.grpc.ManagedChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.grpc.VertxChannelBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BalanceEventBus extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        ManagedChannel channel = VertxChannelBuilder
                .forAddress(vertx, "localhost", 9091)
                .usePlaintext()
                .build();
        ApiBalanceGrpc.ApiBalanceVertxStub stub = ApiBalanceGrpc.newVertxStub(channel);
        vertx.eventBus().consumer("atm.withdrawal", event -> {
            String body = (String) event.body();
            JsonObject joReq = new JsonObject(body);
            JsonObject objReq = new JsonObject();
            String message = joReq.getString("message");
            com.ntnn.grpc.atm.Request requestGrpc = Request.newBuilder()
                    .setErrorCode(200)
                    .setMessage("He want authentication")
                    .setResult(true)
                    .setData(message)
                    .build();
            stub.withdrawal(requestGrpc, handler -> {
                if(handler.failed()) {
                    log.info("Message is received id fail");
                    objReq.put("status", false);
                    objReq.put("errorCode", 400);
                    objReq.put("message", "Request is null");
                    objReq.put("data", new JsonObject());
                    event.reply(objReq);
                    return;
                }
                Response response = handler.result();
                if(!response.getResult()) {
                    log.info("Message is error: "+ response.getMessage());
                    JsonObject dataReply = new JsonObject();
                    dataReply.put("message", response.getMessage());
                    dataReply.put("errorCode", response.getErrorCode());
                    dataReply.put("status", response.getResult());
                    dataReply.put("data", new JsonObject());
                    event.reply(dataReply);
                    return;
                }
                String strDataReply = response.getData();
                JsonObject dataReply = new JsonObject(strDataReply);
                event.reply(dataReply.toString());
            });
        });
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {

    }
}

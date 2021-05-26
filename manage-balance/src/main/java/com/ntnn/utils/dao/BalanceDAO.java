package com.ntnn.utils.dao;

import com.ntnn.utils.database.DatabaseUtils;
import com.ntnn.utils.model.Balance;
import com.ntnn.utils.model.User;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class BalanceDAO implements IDBCommand{
    private Vertx vertx;
    private String name;

    public BalanceDAO(Vertx vertx, String name) {
        this.vertx = vertx;
        this.name = name;
    }
    @Override
    public void select(JsonObject data, Handler<JsonObject> whenDone) {

    }

    @Override
    public void selectById(JsonObject data, Handler<JsonObject> whenDone) {

    }

    public void selectByCardNumber(JsonObject data, Handler<JsonObject> whenDone) {
        String id = data.getString("cardNumber");
        JsonArray params = new JsonArray();
        params.add(id);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "SELECT * FROM balance b WHERE b.cardNumber = ?;";
        client.querySingleWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", asyncResult.cause());
                log.error(asyncResult.cause());
                whenDone.handle(result);
                return;
            }
            JsonArray set = asyncResult.result();
            if(set == null) {
                JsonObject result = new JsonObject();
                result.put("result", true);
                result.put("message", "Success");
                result.put("data", new JsonObject());
                whenDone.handle(result);
                return;
            }
            Balance results = parseToBalance(set);
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results.toJson());
            whenDone.handle(result);
        });
    }

    @Override
    public void insert(JsonObject data, Handler<JsonObject> whenDone) {

    }

    @Override
    public void update(JsonObject data, Handler<JsonObject> whenDone) {
        JsonArray params = parseToParams(data);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql ="UPDATE coreBanking.balance t " +
                "SET t.lastAmount = ?, t.currentAmount = ?, t.lastUpdate = ? WHERE t.cardNumber = ?;";
        client.querySingleWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", "Can't select");
                whenDone.handle(result);
                return;
            }
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", "");
            whenDone.handle(result);
        });
    }

    @Override
    public void delete(JsonObject data, Handler<JsonObject> whenDone) {

    }

    @Override
    public void upsert(JsonObject data, Handler<JsonObject> whenDone) {

    }

    private Balance parseToBalance(JsonArray jsonArray) {
        return Balance.builder()
                .id(jsonArray.getLong(0))
                .userId(jsonArray.getLong(1))
                .cardNumber(jsonArray.getString(2))
                .lastAmount(jsonArray.getDouble(3))
                .currentAmount(jsonArray.getDouble(4))
                .lastUpdate(System.currentTimeMillis()+"")
                .build();
    }

    private JsonArray parseToParams(JsonObject balance) {
        return new JsonArray()
                .add(balance.getDouble("lastAmount"))
                .add(balance.getDouble("currentAmount"))
                .add(balance.getString(System.currentTimeMillis() + ""))
                .add(balance.getString("cardNumber"));
    }
}

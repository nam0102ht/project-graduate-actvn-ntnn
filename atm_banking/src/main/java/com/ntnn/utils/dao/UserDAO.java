package com.ntnn.utils.dao;

import com.ntnn.utils.database.DatabaseUtils;
import com.ntnn.utils.model.User;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserDAO implements IDBCommand{
    private Vertx vertx;

    public UserDAO(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void select(JsonObject data, Handler<JsonObject> whenDone) {
        String limitFrom = data.getString("limitFrom", "0");
        String limitTo = data.getString("limitTo", "100");
        String orderTo = data.getString("sort", "DESC");
        JsonArray params = new JsonArray();
        params.add(limitFrom);
        params.add(limitTo);
        params.add(orderTo);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "SELECT * FROM users LIMIT ?, ? ORDER BY ?;";
        client.queryWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", "Can't select");
                whenDone.handle(result);
                return;
            }
            ResultSet set = asyncResult.result();
            List<JsonArray> lst = set.getResults();
            JsonArray results = new JsonArray();
            lst.forEach(user -> {
                results.add(parseToUser(user));
            });
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results);
            whenDone.handle(result);
        });
    }

    @Override
    public void selectById(JsonObject data, Handler<JsonObject> whenDone) {
        long id = data.getLong("userId");
        JsonArray params = new JsonArray();
        params.add(id);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "SELECT * FROM users u WHERE u.id = ?;";
        client.querySingleWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", "Can't select");
                whenDone.handle(result);
                return;
            }
            JsonArray set = asyncResult.result();
            User results = parseToUser(set);
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results);
            whenDone.handle(result);
        });
    }

    public void selectByCardNumber(JsonObject data, Handler<JsonObject> whenDone) {
        long id = data.getLong("cardNumber");
        JsonArray params = new JsonArray();
        params.add(id);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "SELECT * FROM users u WHERE u.cardNumber = ?;";
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
            User results = parseToUser(set);
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results.toJson());
            whenDone.handle(result);
        });
    }

    @Override
    public void insert(JsonObject data, Handler<JsonObject> whenDone) {
        JsonArray params = parseToParams(data);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "INSERT INTO users(name, username, password, pin," +
                "balanceId, idNumber, phone, email, isAuthenticate, dateOfBirth," +
                "createDate, updateDate) VALUES (?, ?, ?, ?," +
                "?, ?, ?, ?, ?, ?," +
                "?, ?);";
        client.querySingleWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", "Can't select");
                whenDone.handle(result);
                return;
            }
            JsonArray set = asyncResult.result();
            User results = parseToUser(set);
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results);
            whenDone.handle(result);
        });
    }

    @Override
    public void update(JsonObject data, Handler<JsonObject> whenDone) {
        JsonArray params = parseToUpdate(data);
        JDBCClient client = DatabaseUtils.connect(vertx);
        String sql = "UPDATE users SET name = ?, " +
                "username = ?, " +
                "password = ?, " +
                "pin = ?, " +
                "balanceId = ?, " +
                "idNumber = ?, " +
                "phone = ?, " +
                "email = ?, " +
                "isAuthenticate = ?, " +
                "dateOfBirth = ?, " +
                "createDate = ?, " +
                "updateDate = ? " +
                "WHERE cardNumber = ?;";
        client.querySingleWithParams(sql, params, asyncResult -> {
            if(asyncResult.failed()) {
                JsonObject result = new JsonObject();
                result.put("result", false);
                result.put("message", "Can't select");
                whenDone.handle(result);
                return;
            }
            JsonArray set = asyncResult.result();
            User results = parseToUser(set);
            JsonObject result = new JsonObject();
            result.put("result", true);
            result.put("message", "Success");
            result.put("data", results);
            whenDone.handle(result);
        });
    }

    @Override
    public void delete(JsonObject data, Handler<JsonObject> whenDone) {

    }

    @Override
    public void upsert(JsonObject data, Handler<JsonObject> whenDone) {

    }

    public JsonArray parseToUpdate(JsonObject object) {
        JsonArray array = new JsonArray();
        String name = object.getString("name");
        String username = object.getString("username");
        String cardNumber = object.getString("cardNumber");
        String password = object.getString("password");
        String pin = object.getString("pin"); //need hashing by argon2
        long balanceId = object.getInteger("balanceId");
        String idNumber = object.getString("idNumber");
        String phone = object.getString("phone");
        String email = object.getString("email");
        boolean isAuthenticate = object.getBoolean("isAuthenticate");
        String dateOfBirth = object.getString("dateOfBirth");
        String createDate = object.getString("createDate");
        String updateDate = object.getString("updateDate");
        array.add(name)
                .add(username)
                .add(password)
                .add(pin)
                .add(balanceId)
                .add(idNumber)
                .add(phone)
                .add(email)
                .add(isAuthenticate)
                .add(dateOfBirth)
                .add(createDate)
                .add(updateDate)
                .add(cardNumber);
        return array;
    }

    public JsonArray parseToParams(JsonObject object) {
        JsonArray array = new JsonArray();
        String name = object.getString("name");
        String username = object.getString("username");
        String cardNumber = object.getString("cardNumber");
        String password = object.getString("password");
        String pin = object.getString("pin"); //need hashing by argon2
        long balanceId = object.getInteger("balanceId");
        String idNumber = object.getString("idNumber");
        String phone = object.getString("phone");
        String email = object.getString("email");
        boolean isAuthenticate = object.getBoolean("isAuthenticate");
        String dateOfBirth = object.getString("dateOfBirth");
        String createDate = object.getString("createDate");
        String updateDate = object.getString("updateDate");
        array.add(name)
                .add(username)
                .add(cardNumber)
                .add(password)
                .add(pin)
                .add(balanceId)
                .add(idNumber)
                .add(phone)
                .add(email)
                .add(isAuthenticate)
                .add(dateOfBirth)
                .add(createDate)
                .add(updateDate);
        return array;
    }

    public User parseToUser(JsonArray lst) {
        return User.builder()
                .id(lst.getLong(0))
                .name(lst.getString(1))
                .cardNumber(lst.getString(2))
                .username(lst.getString(3))
                .password(lst.getString(4))
                .pin(lst.getString(5))
                .balanceId(lst.getLong(6))
                .idNumber(lst.getString(7))
                .phone(lst.getString(8))
                .email(lst.getString(9))
                .isAuthenticate(lst.getBoolean(10))
                .dateOfBirth(lst.getString(11))
                .createDate(lst.getString(12))
                .updateDate(lst.getString(13)).build();
    }
}

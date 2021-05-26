package com.ntnn.main;

import com.ntnn.grpc.auth.Request;
import com.ntnn.utils.ecc.ECDHUtils;
import com.ntnn.utils.utils.BackendError;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;

@Log4j2
public class MainApiFunction extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.route(HttpMethod.POST, "/api/v2/auth/authentication")
                .handler(handler -> {
                    JsonObject objReq = new JsonObject();
                    HttpServerResponse response = handler.response();
                    if(handler.failed()) {
                        log.info("Something is error!");
                        objReq.put("status", false);
                        objReq.put("errorCode", 400);
                        objReq.put("message", "Fail");
                        objReq.put("data", new JsonObject());
                        response.end(objReq.encode());
                        return;
                    }
                    String requestStr = handler.getBodyAsString();
                    if(requestStr == null) {
                        log.info("Request is null");
                        objReq.put("status", false);
                        objReq.put("errorCode", 400);
                        objReq.put("message", "Request is null");
                        objReq.put("data", new JsonObject());
                        response.end(objReq.encode());
                        return;
                    }
                    JsonObject request = new JsonObject(requestStr);
                    JsonObject data = request.getJsonObject("data", new JsonObject());
                    vertx.eventBus().send("authenticate", data.toString()
                    , reply -> {
                        if(reply.failed()) {
                            log.info("Can't reply to event auth!");
                            objReq.put("status", false);
                            objReq.put("errorCode", 400);
                            objReq.put("message", "Fail");
                            objReq.put("data", new JsonObject());
                            response.end(objReq.encode());
                            return;
                        }
                        response.end(Json.encodePrettily(reply.result().body()));
                    });
                });
        router.route(HttpMethod.POST, "/api/v2/atm/withdrawal")
            .handler(handler -> {
                JsonObject objReq = new JsonObject();
                HttpServerResponse response = handler.response();
                if(handler.failed()) {
                    log.info("Something is error!");
                    objReq.put("status", false);
                    objReq.put("errorCode", 400);
                    objReq.put("message", "Fail");
                    objReq.put("data", new JsonObject());
                    response.end(objReq.encode());
                    return;
                }
                String requestStr = handler.getBodyAsString();
                if(requestStr == null) {
                    log.info("Request is null");
                    objReq.put("status", false);
                    objReq.put("errorCode", 400);
                    objReq.put("message", "Request is null");
                    objReq.put("data", new JsonObject());
                    response.end(objReq.encode());
                    return;
                }
                JsonObject request = new JsonObject(requestStr);
                JsonObject data = request.getJsonObject("data", new JsonObject());
                String message = data.getString("message");
                if(message == null) {
                    log.info("Not found value message!");
                    objReq.put("status", false);
                    objReq.put("errorCode", BackendError.NOT_FOUND);
                    objReq.put("message", "Fail");
                    objReq.put("data", new JsonObject());
                    response.end(objReq.encode());
                    return;
                }
                String spec = data.getString("spec");
                byte[] specBytes = Hex.decode(spec);
                try {
                    message = ECDHUtils.decrypt(message, specBytes);
                    if(message == null) {
                        log.info("Message can't decrypt, please try again!");
                        objReq.put("status", false);
                        objReq.put("errorCode", BackendError.NOT_FOUND);
                        objReq.put("message", "Fail");
                        objReq.put("data", new JsonObject());
                        response.end(objReq.encode());
                        return;
                    }
                    data.put("message", message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vertx.eventBus().request("atm.withdrawal", data.toString()
                    , reply -> {
                        if(reply.failed()) {
                            log.info("Can't reply to event atm.withdrawal!");
                            objReq.put("status", false);
                            objReq.put("errorCode", 400);
                            objReq.put("message", "Fail");
                            objReq.put("data", new JsonObject());
                            response.end(objReq.encode());
                            return;
                        }
                        response.end(Json.encodePrettily(reply.result().body()));
                    });
                });
        server.requestHandler(router).listen(8000);
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {

    }
}

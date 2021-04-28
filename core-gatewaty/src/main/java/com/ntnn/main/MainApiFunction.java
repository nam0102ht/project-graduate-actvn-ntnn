package com.ntnn.main;

import com.ntnn.grpc.auth.Request;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.log4j.Log4j2;

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
                        response.end(reply.result().body().toString());
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
                    vertx.eventBus().send("atm.withdrawal", data.toString()
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
                                response.end(reply.result().body().toString());
                            });
                });
        server.requestHandler(router).listen(8000);
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {

    }
}

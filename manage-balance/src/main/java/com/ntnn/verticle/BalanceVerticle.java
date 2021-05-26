package com.ntnn.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BalanceVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        VertxServer server = VertxServerBuilder
                .forAddress(vertx, "localhost", 9091)
                .addService(new ServiceImpl(vertx, "ServiceImpl"))
                .build();
        server.start(whenDone -> {
            if(whenDone.failed()) {
                log.error("Can't run server {}", whenDone.cause());
                return;
            }
            log.info("----------------------------------");
            log.info("|                                 |");
            log.info("|             ()-()               |");
            log.info("|             ( ^.^)              |");
            log.info("|             | u u]              |");
            log.info("|             [ u u ]             |");
            log.info("|                                 |");
            log.info("-----------------------------------");
            log.info("---------SERVER IS RUNNING---------");
            log.info("-----------------------------------");
        });
    }
}

package com.ntnn.grpc;

import io.vertx.core.AbstractVerticle;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        VertxServer server = VertxServerBuilder
                .forAddress(vertx, "localhost", 9090)
                .addService(new ServiceImp(vertx, "ServiceImpl"))
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

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

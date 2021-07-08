package com.ntnn.main;

import io.vertx.core.Vertx;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
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
        vertx.deployVerticle(new MainApiFunction());
        vertx.deployVerticle(new AuthEventBus());
        vertx.deployVerticle(new BalanceEventBus());
    }
}

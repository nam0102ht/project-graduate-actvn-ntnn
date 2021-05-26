package com.ntnn;

import com.ntnn.verticle.BalanceVerticle;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new BalanceVerticle());
    }
}

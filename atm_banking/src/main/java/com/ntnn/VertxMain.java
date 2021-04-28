package com.ntnn;

import com.ntnn.grpc.Main;
import io.vertx.core.Vertx;

public class VertxMain {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Main());
    }
}

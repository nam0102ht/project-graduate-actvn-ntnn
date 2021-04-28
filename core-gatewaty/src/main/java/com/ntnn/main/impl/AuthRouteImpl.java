package com.ntnn.main.impl;

import com.ntnn.main.AuthRoute;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRouteImpl implements AuthRoute {
    private Vertx vertx;
    private Router router;

    @Override
    public JsonObject authentication(JsonObject request) {

        return null;
    }
}

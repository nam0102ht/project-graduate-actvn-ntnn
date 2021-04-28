package com.ntnn.main;

import io.vertx.core.json.JsonObject;

public interface AuthRoute {
    JsonObject authentication(JsonObject request);
}

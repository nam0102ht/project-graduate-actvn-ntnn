package com.ntnn.utils.jwt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log4j2
public class JwtUtils {

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
    public static String readFile(String nameFile) throws IOException {
        String resource = "keys/" + nameFile;
        InputStream stream = JwtUtils.class.getClassLoader().getResourceAsStream(resource);
        return readFromInputStream(stream);
    }

    public static JWTAuth getProvider(Vertx vertx) {
        JWTAuth provider = null;
        try {
             provider = JWTAuth.create(vertx, new JWTAuthOptions()
                    .addPubSecKey(new PubSecKeyOptions()
                            .setAlgorithm("ES256")
                            .setPublicKey(readFile("public.pem"))
                    ));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return provider;
    }

    public static String jwt(Vertx vertx, JsonObject object) {
        JWTAuth provider = getProvider(vertx);
        return provider.generateToken(object, new JWTOptions().setAlgorithm("ES256"));
    }

    public static void verify(Vertx vertx, JsonObject object, Handler<User> handler) {
        JWTAuth provider = getProvider(vertx);
        provider.authenticate(object, whenDone -> {
            if(whenDone.failed()) {
                handler.handle(null);
                return;
            }
            User user = whenDone.result();
            handler.handle(user);
        });
    }
}

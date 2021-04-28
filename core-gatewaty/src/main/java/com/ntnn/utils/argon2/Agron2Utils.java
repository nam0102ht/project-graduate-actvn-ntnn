package com.ntnn.utils.argon2;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Agron2Utils {
    public static String hash(String plainText) {
        Argon2 argon2 = Argon2Factory.create();
        String hash = "";
        try {
            // iterations = 20
            // memory = 64m
            // parallelism = 1
            hash = argon2.hash(20, 65536, 1, plainText.toCharArray());

        } finally {
            // Wipe confidential data
            argon2.wipeArray(plainText.toCharArray());
        }
        return hash;
    }

    public static boolean verify(String hashText, String rawText) {
        Argon2 argon2 = Argon2Factory.create();
        if (argon2.verify(hashText, rawText.toCharArray())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Agron2Utils.hash("111111"));
        System.out.println(Agron2Utils.verify("$argon2i$v=19$m=65536,t=20,p=1$wM69jb5UrYTw8qWB42++ug$b5p0TJM5M02OMxxn5EhXVkBGp2O2wO9uS1brg73GdEY", "111111"));
    }
}

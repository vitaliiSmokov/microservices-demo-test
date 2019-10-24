package io.swagger.petstore.api.utils;

import java.util.Base64;

/**
 * @author Vitalii Smokov 05.02.2019
 */
public class Base64Util {

    public static String encodeToBase64(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    public static String decodeFromBase64(String string) {
        return new String(Base64.getDecoder().decode(string.getBytes()));
    }
}

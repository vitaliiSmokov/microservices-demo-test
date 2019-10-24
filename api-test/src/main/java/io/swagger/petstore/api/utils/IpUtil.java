package io.swagger.petstore.api.utils;

import java.net.URL;
import java.util.Scanner;

/**
 * @author Vitalii Smokov 19.12.2018
 */
public class IpUtil {

    private static String ip;

    public static String getCurrentIP() {
        try (Scanner s = new Scanner(new URL("https://api.ipify.org").openStream(), "UTF-8")
                .useDelimiter("\\A")) {
            ip = s.next();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return ip;
    }
}

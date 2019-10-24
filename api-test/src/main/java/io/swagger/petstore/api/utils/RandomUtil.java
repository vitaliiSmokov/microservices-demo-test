package io.swagger.petstore.api.utils;

import com.devskiller.jfairy.Fairy;
import com.github.javafaker.Faker;

import java.util.Random;

/**
 * @author Vitalii Smokov 19.03.2019
 */
public class RandomUtil {

    private static Faker faker;
    private static Fairy fairy;

    public static String getRandomHexString(int numbers) {
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        while (stringBuffer.length() < numbers) {
            stringBuffer.append(Integer.toHexString(random.nextInt()));
        }
        return stringBuffer.toString().substring(0, numbers);
    }

    public static Faker getFaker() {
        if (faker == null) {
            faker = new Faker();
        }
        return faker;
    }

    public static Fairy getFairy() {
        if (fairy == null) {
            fairy = Fairy.create();
        }
        return fairy;
    }
}

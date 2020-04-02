package io.swagger.petstore.api.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FakerUtil {

    private static Faker faker;

    public static Faker getFaker() {
        if (faker == null) {
            faker = new Faker();
        }
        return faker;
    }

    public String getRandomCharacters(int number){
        return getFaker().lorem().characters(number);
    }
}

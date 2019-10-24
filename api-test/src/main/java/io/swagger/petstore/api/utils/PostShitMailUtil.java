package io.swagger.petstore.api.utils;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Vitalii Smokov 25.04.2019
 * <p>
 * https://www.guerrillamail.com/GuerrillaMailAPI.html https://rapidapi.com/Privatix/api/temp-mail?utm_source=mashape&utm_medium=301#get-message-attachments
 * https://post-shift.ru/api/
 */
@Slf4j
public class PostShitMailUtil {

    private static int counter = 0;

    public static String createMail10minute() {
        return (String) RestAssured
                .given()
                .log().all()
                .when()
                .get("https://post-shift.ru/api.php?action=new&type=json")
                .then()
                .log().all()
                .extract().body().as(Map.class).get("mail");
    }

}

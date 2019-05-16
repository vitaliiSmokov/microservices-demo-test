package com.socks.api.utils;

import io.restassured.*;
import java.util.*;
import lombok.extern.slf4j.*;

/**
 * @author Vitalii Smokov 25.04.2019
 *
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

package com.socks.api.utils;

import static com.lukspay.api.conditions.Conditions.*;
import static org.hamcrest.Matchers.*;

import com.lukspay.api.*;
import java.util.*;

/**
 * @author Vitalii Smokov 13.02.2019
 */
public class ResponseUtil {

  public static Map<String, String> extractHeadersFromResponse(AssertableResponse response,
      String... headersList) {
    Map<String, String> headers = new HashMap<>();
    for (String header : headersList) {
      response.shouldHave(header(header, not(isEmptyString())));
      headers.put(header, response.getResponse().extract().header(header));
    }
    return headers;
  }

  public static <T> T extractDtoFromResponse(AssertableResponse response, Class<T> dtoClass) {
    return response.getResponse().extract().as(dtoClass);
  }
}

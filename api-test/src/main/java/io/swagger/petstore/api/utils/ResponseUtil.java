package io.swagger.petstore.api.utils;

import io.swagger.petstore.api.assertions.AssertableResponse;
import io.swagger.petstore.api.conditions.Conditions;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

/**
 * @author Vitalii Smokov 13.02.2019
 */
public class ResponseUtil {

    public static Map<String, String> extractHeadersFromResponse(AssertableResponse response,
                                                                 String... headersList) {
        Map<String, String> headers = new HashMap<>();
        for (String header : headersList) {
            response.shouldHave(Conditions.header(header, not(isEmptyString())));
            headers.put(header, response.getResponse().then().extract().header(header));
        }
        return headers;
    }

    public static <T> T extractDtoFromResponse(AssertableResponse response, Class<T> dtoClass) {
        return response.getResponse().then().extract().as(dtoClass);
    }
}

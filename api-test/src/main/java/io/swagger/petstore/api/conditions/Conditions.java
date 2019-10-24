package io.swagger.petstore.api.conditions;

import org.hamcrest.Matcher;

public class Conditions {

    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public static BodyCondition body(String id, Matcher<?> matcher) {
        return new BodyCondition(id, matcher);
    }

    public static HeaderCondition header(String id, Matcher<?> matcher) {
        return new HeaderCondition(id, matcher);
    }
}

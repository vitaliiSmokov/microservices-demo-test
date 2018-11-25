package com.socks.api.conditions;

import org.hamcrest.Matcher;

public class Conditions {

    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public static BodyCondition body(String id, Matcher<String> matcher) {
        return new BodyCondition(id , matcher);
    }
}

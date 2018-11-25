package com.socks.api.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

    private int statusCode;

//    StatusCodeCondition(int code) {
//        statusCode = code;
//    }

    @Override
    public void verify(ValidatableResponse validatableResponse) {
        validatableResponse.assertThat().statusCode(statusCode);
    }

    @Override
    public String toString() {
        return "status code is " + statusCode;
    }
}

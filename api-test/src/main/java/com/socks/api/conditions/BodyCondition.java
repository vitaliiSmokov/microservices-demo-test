package com.socks.api.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

@AllArgsConstructor
public class BodyCondition implements Condition {

    private String jsonPath;
    private Matcher<String> matcher;

//    BodyCondition(String jsonPath, Matcher<String> matcher) {
//        this.jsonPath = jsonPath;
//        this.matcher = matcher;
//    }

    @Override
    public void verify(ValidatableResponse validatableResponse) {

        validatableResponse.assertThat().body(jsonPath, matcher);
    }

    @Override
    public String toString() {
        return "body " + jsonPath + matcher;
    }
}

package io.swagger.petstore.api.conditions;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import static org.hamcrest.Matchers.not;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

    private int statusCode;

    @Override
    public void verify(Response response) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Override
    public void verifyNot(Response response) {
        response.then().statusCode(not(statusCode));
    }

    @Override
    public String toString() {
        return "status code is " + statusCode;
    }
}

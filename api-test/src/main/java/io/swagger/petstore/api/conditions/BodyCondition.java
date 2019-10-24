package io.swagger.petstore.api.conditions;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.not;

@AllArgsConstructor
public class BodyCondition implements Condition {

    private String jsonPath;
    private Matcher<?> matcher;

    @Override
    public void verify(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
    }

    @Override
    public void verifyNot(Response response) {
        response.then().assertThat().body(jsonPath, not(matcher));
    }

    @Override
    public String toString() {
        return "body \"".concat(jsonPath).concat("\": is ").concat(String.valueOf(matcher));
    }
}

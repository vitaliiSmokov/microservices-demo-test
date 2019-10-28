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
        if (jsonPath != null) {
            response.then().assertThat().body(jsonPath, matcher);
        } else {
            response.then().assertThat().body(matcher);
        }
    }

    @Override
    public void verifyNot(Response response) {
        if (jsonPath != null) {
            response.then().assertThat().body(jsonPath, not(matcher));
        } else {
            response.then().assertThat().body(not(matcher));
        }
    }

    @Override
    public String toString() {
        if (jsonPath != null) {
            return "body \"".concat(jsonPath).concat("\": is ").concat(String.valueOf(matcher));
        }
        return "body ".concat(" is ").concat(String.valueOf(matcher));
    }
}

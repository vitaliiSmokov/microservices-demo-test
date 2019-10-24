package io.swagger.petstore.api.conditions;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.not;

/**
 * @author Vitalii Smokov 19.12.2018
 */
@AllArgsConstructor
public class HeaderCondition implements Condition {

    private String name;
    private Matcher<?> matcher;

    @Override
    public void verify(Response response) {
        response.then().assertThat().header(name, matcher);
    }

    @Override
    public void verifyNot(Response response) {
        response.then().assertThat().header(name, not(matcher));
    }

    @Override
    public String toString() {
        return "header ".concat(name).concat(" ").concat(String.valueOf(matcher));
    }
}

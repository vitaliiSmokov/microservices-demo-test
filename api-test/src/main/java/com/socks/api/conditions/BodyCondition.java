package com.socks.api.conditions;

import static org.hamcrest.Matchers.not;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

@AllArgsConstructor
public class BodyCondition implements Condition {

  private String jsonPath;
  private Matcher<?> matcher;

  @Override
  public void verify(ValidatableResponse validatableResponse) {
    validatableResponse.assertThat().body(jsonPath, matcher);
  }

  @Override
  public void verifyNot(ValidatableResponse response) {
    response.assertThat().body(jsonPath, not(matcher));
  }

  @Override
  public String toString() {
    return "body \"".concat(jsonPath).concat("\": is ").concat(String.valueOf(matcher));
  }
}

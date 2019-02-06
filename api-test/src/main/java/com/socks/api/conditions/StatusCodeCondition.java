package com.socks.api.conditions;

import static org.hamcrest.Matchers.not;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

  private int statusCode;

  @Override
  public void verify(ValidatableResponse validatableResponse) {
    validatableResponse.assertThat().statusCode(statusCode);
  }

  @Override
  public void verifyNot(ValidatableResponse response) {
    response.statusCode(not(statusCode));
  }

  @Override
  public String toString() {
    return "status code is " + statusCode;
  }
}

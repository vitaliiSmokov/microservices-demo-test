package com.socks.api.conditions;

import static org.hamcrest.Matchers.not;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

/**
 * @author Vitalii Smokov 19.12.2018
 */
@AllArgsConstructor
public class HeaderCondition implements Condition {

  private String name;
  private Matcher<?> matcher;

  @Override
  public void verify(ValidatableResponse validatableResponse) {
    validatableResponse.assertThat().header(name, matcher);
  }

  @Override
  public void verifyNot(ValidatableResponse response) {
    response.assertThat().header(name, not(matcher));
  }

  @Override
  public String toString() {
    return "header ".concat(name).concat(" ").concat(String.valueOf(matcher));
  }
}

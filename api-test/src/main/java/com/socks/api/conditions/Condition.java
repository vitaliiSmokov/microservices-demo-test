package com.socks.api.conditions;

import io.restassured.response.ValidatableResponse;

public interface Condition {

  void verify(ValidatableResponse validatableResponse);

  void verifyNot(ValidatableResponse response);
}

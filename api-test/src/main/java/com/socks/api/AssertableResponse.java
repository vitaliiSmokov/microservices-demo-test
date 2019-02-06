package com.socks.api;


import com.socks.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

  private final ValidatableResponse response;

  @Step("Api response should have {condition}")
  public AssertableResponse shouldHave(Condition condition) {
    log.info("verify :" + condition);
    condition.verify(response);
    return this;
  }

  public ValidatableResponse getResponse() {
    return response;
  }

  @Step("Api response should not have {condition}")
  public AssertableResponse shouldNotHave(Condition condition) {
    log.info("verify is not:" + condition);
    condition.verifyNot(response);
    return this;
  }

  public <T> T asPojo(String path, Class<T> tClass) {
    log.info("extract response as {}", tClass.getName());
    return response
        .extract()
        .body()
        .jsonPath()
        .getObject(path, tClass);
  }

  public <T> T asPojo(Class<T> tClass) {
    log.info("extract response as {}", tClass.getName());
    return response
        .extract()
        .body()
        .as(tClass);
  }
}

package io.swagger.petstore.api.assertions;

import io.qameta.allure.Step;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.swagger.petstore.api.conditions.Condition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;

    @Step("Api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        log.info("verify :" + condition);
        condition.verify(response);
        return this;
    }

    public Response getResponse() {
        return response;
    }

    @Step("Api response should not have {condition}")
    public AssertableResponse shouldNotHave(Condition condition) {
        log.info("verify is not:" + condition);
        condition.verifyNot(response);
        return this;
    }

    public <T> T asPojo(Class<T> tClass) {
        log.info("extract response as {}", tClass.getName());
        return response
                .then()
                .extract()
                .body()
                .as(tClass);
    }

    public <T> T asPojo(String path, Class<T> tClass) {
        log.info("extract response as {}", tClass.getName());
        return response
                .then()
                .extract()
                .body()
                .jsonPath()
                .getObject(path, tClass);
    }

    public <T> List<T> asListPojo(String path, Class<T> tClass) {
        return response
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(path, tClass);
    }

    public <T> List<T> asListPojo(String path) {
        return response
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(path);
    }

    public Headers headers() {
        return response.getHeaders();
    }
}

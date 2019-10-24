package io.swagger.petstore.api.conditions;

import io.restassured.response.Response;

public interface Condition {

    void verify(Response response);

    void verifyNot(Response response);
}

package io.swagger.petstore.api.services;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.swagger.petstore.api.AssertableResponse;
import io.swagger.petstore.api.model.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService extends AuthBase {

    @Step("Register user: {user}")
    public AssertableResponse registerUser(UserDTO user) {
        log.info("register user: {}", user);
        return new AssertableResponse(
                postResource(initSpec(ContentType.JSON), "user", user)
        );
    }
}

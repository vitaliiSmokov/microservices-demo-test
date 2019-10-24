package io.swagger.petstore.api.services;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.swagger.petstore.api.assertions.AssertableResponse;
import io.swagger.petstore.api.endpoints.UserServiceEnpoints;
import io.swagger.petstore.api.model.UserDTO;
import lombok.extern.slf4j.Slf4j;

import static io.swagger.petstore.api.endpoints.UserServiceEnpoints.USER;
import static io.swagger.petstore.api.endpoints.UserServiceEnpoints.USER_BY_USERNAME;

@Slf4j
public class UserApiService extends AuthBase {

    @Step("Register user: {user}")
    public AssertableResponse registerUser(UserDTO user) {
        log.info("register user: {}", user);
        return new AssertableResponse(
                postResource(initSpec(ContentType.JSON), USER.getUrl(), user)
        );
    }

    @Step("Get user by username")
    public AssertableResponse getUserByUsername(UserDTO user) {
        log.info("Get user by username: '{}'", user.username());
        return new AssertableResponse(
                getResource(initSpec(ContentType.JSON), USER_BY_USERNAME.getUrl(user.username())));
    }
}

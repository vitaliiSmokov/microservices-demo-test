package io.swagger.petstore.api.services;

import io.qameta.allure.Step;
import io.swagger.petstore.api.assertions.AssertableResponse;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.params.UserParams;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.http.ContentType.JSON;
import static io.swagger.petstore.api.endpoints.UserServiceEnpoints.*;

@Slf4j
public class UserApiService extends AuthBase {

    @Step("Register user: {user}")
    public AssertableResponse registerUser(UserDTO user) {
        log.info("register user: {}", user);
        return new AssertableResponse(
                postResource(initSpec(JSON), USER.getUrl(), user)
        );
    }

    @Step("Get user: {user} by username")
    public AssertableResponse getUserByUsername(UserDTO user) {
        log.info("Get user by username: '{}'", user.username());
        return new AssertableResponse(
                getResource(initSpec(JSON), USER_BY_USERNAME.getUrl(user.username())));
    }

    @Step("Update user: {user}")
    public AssertableResponse updateUser(UserDTO user) {
        return new AssertableResponse(
                putResource(initSpec(JSON), USER_BY_USERNAME.getUrl(user.username()), user)
        );
    }

    @Step("Delete user: {user}")
    public AssertableResponse deleteUser(UserDTO user) {
        return new AssertableResponse(
                deleteResource(initSpec(JSON), USER_BY_USERNAME.getUrl(user.username()))
        );
    }

    @Step("Login user: {user}")
    public AssertableResponse loginUser(UserDTO user) {
        return new AssertableResponse(
                getResourceWithParams(
                        initSpec(JSON),
                        USER_LOGIN.getUrl(),
                        UserParams.builder()
                                .addUsername(user.username())
                                .addPassword(user.password())
                                .build()
                                .getParams()
                )
        );
    }

    @Step("Logout user")
    public AssertableResponse logoutUser() {
        return new AssertableResponse(
                getResource(initSpec(JSON), USER_LOGIN.getUrl())
        );
    }
}

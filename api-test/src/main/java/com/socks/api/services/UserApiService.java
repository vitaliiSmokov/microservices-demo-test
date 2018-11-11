package com.socks.api.services;

import com.socks.api.model.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService {

    public RequestSpecification setUp() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//                .log().all();
    }

    public ValidatableResponse registerUser(User user) {
        log.info("register user: {}", user);

        return setUp()
                .body(user)
                .when()
                .post("register")
                .then();
    }
}

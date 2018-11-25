package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.model.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

//@Slf4j
public class UserApiService {

    public RequestSpecification setUp() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filters(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter(),
                        new AllureRestAssured()
                );
//                .log().all();
    }

    @Step
    public AssertableResponse registerUser(User user) {
//        Logger log = LoggerFactory.getLogger(UserApiService.class);
//        log.info("register user: {}", user);

        return new AssertableResponse(setUp()
                .body(user)
                .when()
                .post("register")
                .then());
    }
}

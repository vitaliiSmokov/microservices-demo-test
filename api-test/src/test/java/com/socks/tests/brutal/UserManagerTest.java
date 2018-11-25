package com.socks.tests.brutal;

import com.socks.api.conditions.Conditions;
import com.socks.api.model.User;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.socks.api.conditions.Conditions.body;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

class UserManagerTest {

    private UserApiService userApiService = new UserApiService();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://68.183.67.80/";
    }

    @Test
    void testCanRegisterUserWithValidCredentials() {

//        given
        User user = new User()
                .setFirstName("Vitalii")
                .setLastName("Smokov")
                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setEmail("test@mail.com")
                .setPassword("test123");

//        when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filters(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter()
                ).body(user)
                .when()
                .post("register")
//                Then
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", not(isEmptyString()));
    }

    @Test
    void testCanRegisterUserWithInValidCredentials() {

        User user = new User()
                .setFirstName("Vitalii")
                .setLastName("Smokov")
                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setEmail("test@mail.com")
                .setPassword("test123");

        userApiService
                .registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(body("id", not(isEmptyString())));
    }
}

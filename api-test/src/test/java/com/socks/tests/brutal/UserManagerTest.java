package com.socks.tests.brutal;

import com.socks.api.model.User;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class UserManagerTest {

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
        ValidatableResponse response = userApiService
                .registerUser(user);

//        then
        response
                .assertThat()
                .statusCode(200)
                .body("id", not(isEmptyString()));
    }
}

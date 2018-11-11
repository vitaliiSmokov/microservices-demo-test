package com.socks.tests.brutal;

import com.socks.api.model.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserManagerTest {

    @BeforeAll
    void setUp() {
        RestAssured.baseURI = "";
    }

    @Test
    void testCanRegisterUserWithValidCredentials() {
        User user = new User();
    }
}

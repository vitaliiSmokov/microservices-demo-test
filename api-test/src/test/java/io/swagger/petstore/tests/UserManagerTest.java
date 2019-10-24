package io.swagger.petstore.tests;

import com.devskiller.jfairy.producer.person.Person;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import io.swagger.petstore.api.utils.RandomUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.swagger.petstore.api.conditions.Conditions.bodyAsPojo;
import static io.swagger.petstore.api.conditions.Conditions.statusCode;
import static org.apache.http.HttpStatus.SC_OK;

class UserManagerTest {

    private final static UserApiService userApiService = new UserApiService();
    private final static Person person = RandomUtil.getFairy().person();
    private static UserDTO userDTO;


    @BeforeAll
    static void setUp() {
        userDTO = new UserDTO()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .username(person.getUsername())
                .email(person.getEmail())
                .password(person.getPassword());
    }

    @Test
    @DisplayName("Register new user")
    void testCanRegisterUser() {
        userApiService
                .registerUser(userDTO)
                .shouldHave(statusCode(SC_OK));
    }

    @Nested
    class GetUser {

        @Test
        @DisplayName("Get user by user name")
        void TestCanGetUserByUsername() {
            userApiService
                    .getUserByUsername(UserManagerTest.userDTO)
                    .shouldHave(statusCode(SC_OK))
                    .shouldHave(bodyAsPojo(userDTO));
        }

    }
}

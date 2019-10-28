package io.swagger.petstore.tests;

import com.devskiller.jfairy.producer.person.Person;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import io.swagger.petstore.api.utils.RandomUtil;
import org.junit.jupiter.api.*;

import static io.swagger.petstore.api.conditions.Conditions.bodyAsPojo;
import static io.swagger.petstore.api.conditions.Conditions.statusCode;
import static org.apache.http.HttpStatus.SC_OK;

class UserManagerTest {

    private final static UserApiService userApiService = new UserApiService();
    private final static Person person = RandomUtil.getFairy().person();
    private static UserDTO userDTO;


    @BeforeAll
    static void createUserDTO() {
        userDTO = new UserDTO()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .username(person.getUsername())
                .email(person.getEmail())
                .password(person.getPassword());
    }

    @AfterAll
    static void deleteUser() {
        if (userDTO != null) {
            userApiService
                    .deleteUser(userDTO)
                    .shouldHave(statusCode(SC_OK));
        }
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
        void testCanGetUserByUsername() {
            userApiService
                    .getUserByUsername(userDTO)
                    .shouldHave(statusCode(SC_OK))
                    .shouldHave(bodyAsPojo(userDTO));
        }
    }

    @Nested
    class UpdateUser {

        @Test
        @DisplayName("Update user")
        void testCanUpdateUser() {
            userApiService
                    .updateUser(
                            userDTO
                                    .firstName(person.getFirstName())
                                    .lastName(person.getLastName())
                    )
                    .shouldHave(statusCode(SC_OK));
        }
    }


}

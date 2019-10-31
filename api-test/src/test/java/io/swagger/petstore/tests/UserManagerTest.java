package io.swagger.petstore.tests;

import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import org.junit.jupiter.api.*;

import static io.swagger.petstore.api.conditions.Conditions.*;
import static io.swagger.petstore.api.generators.UserGenerator.getUserDTO;
import static io.swagger.petstore.api.utils.RandomUtil.getPerson;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.blankString;

@DisplayName("User management tests")
class UserManagerTest {

    private final static UserApiService userApiService = new UserApiService();
    private static UserDTO userDTO;


    @BeforeAll
    static void createUserDTO() {
        userDTO = getUserDTO();
    }

    @AfterAll
    static void deleteUser() {
        if (userDTO != null) {
            userApiService
                    .deleteUser(userDTO);
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
    @DisplayName("Update user")
    class UpdateUser {
        @Test
        @DisplayName("Update user")
        void testCanUpdateUser() {
            userApiService
                    .updateUser(
                            userDTO
                                    .firstName(getPerson().getFirstName())
                                    .lastName(getPerson().getLastName())
                    )
                    .shouldHave(statusCode(SC_OK))
                    .shouldHave(body(blankString()));
        }

        @Nested
        @DisplayName("Get user")
        class GetUser {
            @Test
            @DisplayName("Get user by user name")
            void testCanGetUserByUsername() {
                userApiService
                        .getUserByUsername(userDTO)
                        .shouldHave(statusCode(SC_OK))
                        .shouldHave(bodyAsPojo(userDTO));
            }

            @Nested
            @DisplayName("Delete user")
            class DeleteUser {
                @Test
                @DisplayName("Delete user by name")
                void testCanDeleteUser() {
                    userApiService.deleteUser(userDTO)
                            .shouldHave(statusCode(SC_OK))
                            .shouldHave(body(blankString()));
                    userDTO = null;
                }
            }
        }
    }
}

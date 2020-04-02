package io.swagger.petstore.tests;

import io.swagger.petstore.api.generators.UserGenerator;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import org.junit.jupiter.api.*;

import static io.swagger.petstore.api.conditions.Conditions.body;
import static io.swagger.petstore.api.conditions.Conditions.statusCode;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;

@DisplayName("Login and logout user")
public class LoginTest extends BaseApiTest{

    private static final UserApiService userApiService = new UserApiService();
    static UserDTO userDTO;

    @BeforeAll
    static void registerUser() {
        userDTO = UserGenerator.getUserDTO();
        userApiService.registerUser(userDTO);
    }

    @AfterAll
    static void deleteUser() {
        if (userDTO != null) {
            userApiService
                    .deleteUser(userDTO);
        }
    }

    @Test
    @DisplayName("Login user")
    void testCanLoginUser() {
        userApiService.loginUser(userDTO)
                .shouldHave(statusCode(SC_OK))
                .shouldHave(body(containsString("logged in user session:")));
    }

    @Nested
    @DisplayName("Logout user")
    class LogoutUser {

        @Test
        @DisplayName("Logout user")
        void testCanLogoutUser() {
            userApiService.logoutUser()
                    .shouldHave(statusCode(SC_OK));
        }
    }
}

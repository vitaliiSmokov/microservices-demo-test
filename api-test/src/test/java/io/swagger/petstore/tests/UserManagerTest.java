package io.swagger.petstore.tests;

import io.swagger.petstore.api.model.ResponseDTO;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static io.swagger.petstore.api.conditions.Conditions.bodyAsPojo;
import static io.swagger.petstore.api.conditions.Conditions.statusCode;
import static io.swagger.petstore.api.generators.UserGenerator.getUserDTO;
import static io.swagger.petstore.api.utils.RandomUtil.getPerson;
import static org.apache.http.HttpStatus.SC_OK;

@DisplayName("User management tests")
class UserManagerTest {

  private static final UserApiService userApiService = new UserApiService();
  private static UserDTO userDTO;

  @BeforeAll
  static void createUserDTO() {
    userDTO = getUserDTO();
  }

  @AfterAll
  static void deleteUser() {
    if (userDTO != null) {
      userApiService.deleteUser(userDTO);
    }
  }

  @Test
  @DisplayName("Register new user")
  void testCanRegisterUser() {
    ResponseDTO response =
        userApiService
            .registerUser(userDTO)
            .shouldHave(statusCode(SC_OK))
            .asPojo(ResponseDTO.class);
    userDTO.id(Long.parseLong(response.message()));
  }

  private void verifyResponse(ResponseDTO response) {
    SoftAssertions.assertSoftly(
        softAssertions -> {
          softAssertions.assertThat(response.code()).isEqualTo(SC_OK);
          softAssertions.assertThat(response.type()).isNotBlank();
          softAssertions.assertThat(response.message()).isNotBlank();
        });
  }

  @Nested
  @DisplayName("Update user")
  class UpdateUser {
    @Test
    @DisplayName("Update user")
    void testCanUpdateUser() {
      ResponseDTO response =
          userApiService
              .updateUser(
                  userDTO.firstName(getPerson().getFirstName()).lastName(getPerson().getLastName()))
              .shouldHave(statusCode(SC_OK))
              .asPojo(ResponseDTO.class);
      verifyResponse(response);
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
          ResponseDTO responseDTO =
              userApiService
                  .deleteUser(userDTO)
                  .shouldHave(statusCode(SC_OK))
                  .asPojo(ResponseDTO.class);
          verifyResponse(responseDTO);
          Assertions.assertThat(responseDTO.message()).isEqualTo(userDTO.username());
          userDTO = null;
        }
      }
    }
  }
}

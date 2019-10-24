package io.swagger.petstore.tests;

import com.devskiller.jfairy.producer.person.Person;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.services.UserApiService;
import io.swagger.petstore.api.utils.RandomUtil;
import org.junit.jupiter.api.Test;

import static io.swagger.petstore.api.conditions.Conditions.statusCode;

class UserManagerTest {

    private final UserApiService userApiService = new UserApiService();
    private Person person = RandomUtil.getFairy().person();

    @Test
    void testCanRegisterUser() {
        userApiService
                .registerUser(
                        new UserDTO()
                                .firstName(person.getFirstName())
                                .lastName(person.getLastName())
                                .username(person.getUsername())
                                .email(person.getEmail())
                                .password(person.getPassword()))
                .shouldHave(statusCode(200));
    }
}

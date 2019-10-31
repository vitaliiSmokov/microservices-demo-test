package io.swagger.petstore.api.generators;

import com.devskiller.jfairy.producer.person.Person;
import io.swagger.petstore.api.model.UserDTO;
import io.swagger.petstore.api.utils.RandomUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserGenerator {

    private final Person person = RandomUtil.getFairy().person();

    public UserDTO getUserDTO() {
        return new UserDTO()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .username(person.getUsername())
                .email(person.getEmail())
                .password(person.getPassword());
    }
}

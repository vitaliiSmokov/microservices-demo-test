package io.swagger.petstore.api.generators;

import io.swagger.petstore.api.model.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserGenerator extends BaseGenerator {

  public UserDTO getUserDTO() {
    return new UserDTO()
        .firstName(PERSON.getFirstName())
        .lastName(PERSON.getLastName())
        .username(PERSON.getUsername())
        .email(PERSON.getEmail())
        .password(PERSON.getPassword());
  }
}

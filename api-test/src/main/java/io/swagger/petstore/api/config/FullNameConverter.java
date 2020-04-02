package io.swagger.petstore.api.config;

import io.swagger.petstore.api.model.FullNameDTO;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class FullNameConverter implements Converter<FullNameDTO> {

  @Override
  public FullNameDTO convert(Method targetMethod, String text) {
    String[] split = text.split(" ", -1);
    String firstName = split[0];
    String lastName = null;
    if (split.length >= 2) {
      lastName = split[1];
    }
    return new FullNameDTO().firstName(firstName).lastName(lastName);
  }
}

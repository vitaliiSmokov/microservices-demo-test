package io.swagger.petstore.api.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.swagger.petstore.api.utils.DateTimeUtil;

import java.io.IOException;
import java.time.LocalDate;

public class StringToDateDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

    String value = p.readValueAs(String.class);
    return DateTimeUtil.toLocalDate(value);
  }
}

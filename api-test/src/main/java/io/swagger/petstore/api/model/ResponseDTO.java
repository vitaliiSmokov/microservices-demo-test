package io.swagger.petstore.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper=false)
public class ResponseDTO extends BaseDTO {

  @JsonProperty("code")
  private int code;

  @JsonProperty("type")
  private String type;

  @JsonProperty("message")
  private String message;
}

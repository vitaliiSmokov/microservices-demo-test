package io.swagger.petstore.api.utils.db.mongo.enums;

public enum MongoDB {
  posts("posts");

  MongoDB(String name) {
    this.name = name;
  }

  private String name;

  public String getName() {
    return name;
  }
}

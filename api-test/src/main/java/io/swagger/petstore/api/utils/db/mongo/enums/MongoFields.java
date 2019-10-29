package io.swagger.petstore.api.utils.db.mongo.enums;

public enum MongoFields {
  ID("_id"),
  SOURCE_ID("source_id");

  MongoFields(String field) {
    this.field = field;
  }

  private String field;

  public String getField() {
    return field;
  }
}

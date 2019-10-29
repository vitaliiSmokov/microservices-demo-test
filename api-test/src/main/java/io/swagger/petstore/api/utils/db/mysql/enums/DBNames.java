package io.swagger.petstore.api.utils.db.mysql.enums;

public enum DBNames {
  DB_USERS("Users");

  private String dbName;

  DBNames(String dbName) {
    this.dbName = dbName;
  }

  public String getDBName() {
    return dbName;
  }
}

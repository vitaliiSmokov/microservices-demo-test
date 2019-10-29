package io.swagger.petstore.api.utils.db.mysql.enums;

public enum DBTables {
  TOKENS("tokens");

  private String table;

  DBTables(String table) {
    this.table = table;
  }

  public String getTable() {
    return table;
  }
}

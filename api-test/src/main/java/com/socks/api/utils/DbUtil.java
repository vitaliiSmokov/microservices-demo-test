package com.socks.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

  private Properties propertyDB = new PropertyUtil()
      .readPropertyFromFile("src/test/resources/db.properties");

  private final String LOGIN = propertyDB.getProperty("username");
  private final String PASSWORD = propertyDB.getProperty("password");
  private final String DATABASE_NAME = propertyDB.getProperty("db_name");
  private final String DATABASE_ADDRESS = propertyDB.getProperty("db_url");

  private Connection connection = null;
  private Statement statement = null;
  private ResultSet resultSet = null;


  private Connection connectToMySQL() {
    try {
      connection = DriverManager.getConnection(DATABASE_ADDRESS + DATABASE_NAME, LOGIN, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  private Statement createStatement() {
    try {
      statement = connectToMySQL().createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }

  public ResultSet executeQuery(String query) {
    try {
      resultSet = createStatement().executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeAllDBConnections();
    }
    return resultSet;
  }

  public void closeAllDBConnections() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}

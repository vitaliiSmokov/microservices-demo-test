package io.swagger.petstore.api.utils.db.mysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import org.apache.commons.dbutils.QueryRunner;
import services.mysql.enums.DBTables;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.UTIL.logger;

public class DB_Methods {

  public void deleteEntry(DBTables tableFrom, String partOfQuery) {
    String query = String.format("DELETE FROM %1$s WHERE %2$s", tableFrom.getTable(), partOfQuery);

    try {
      System.out.println(">>> " + query);
      new QueryRunner().update(MSQLBase.connection, query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Step("Update table {0}")
  public void updateQuery(DBTables tableFrom, String setSmth, String whereQuery) {
    String query =
        String.format(
            "UPDATE %1$s SET %2$s " + "WHERE %3$s", tableFrom.getTable(), setSmth, whereQuery);
    logger.info(query);

    try {
      new QueryRunner().update(MSQLBase.connection, query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String getSmth(DBTables table, String whatToSelect, String partOfquery) {
    ResultSet rs;
    String query =
        String.format(
            "SELECT %1$s  FROM %2$s WHERE %3$s", whatToSelect, table.getTable(), partOfquery);
    System.out.println(query);
    try {
      rs = MSQLBase.connection.createStatement().executeQuery(query);
      if (rs.next()) {
        return rs.getString(1);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getSmthToMap(String query) {
    List list = new ArrayList();
    ResultSet rs;
    System.out.println(query);
    try {
      rs = MSQLBase.connection.createStatement().executeQuery(query);

      if (rs != null) {
        ResultSetMetaData metaData = rs.getMetaData();
        while (rs.next()) {
          Map<String, Object> columnMap = new HashMap<>();
          for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
            if (rs.getString(metaData.getColumnName(columnIndex)) != null)
              columnMap.put(
                  metaData.getColumnLabel(columnIndex),
                  rs.getString(metaData.getColumnName(columnIndex)));
            else columnMap.put(metaData.getColumnLabel(columnIndex), null);
          }
          list.add(columnMap);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    return gson.toJson(list);
  }

  public <T> List<T> getMySQLObjectEquals(
          DBTables table, String whatToSelect, String partOfquery, Class<T> tClass) {
    String jsonFromMySQL =
        getSmthToMap(
            String.format(
                "SELECT %1$s  FROM %2$s WHERE %3$s", whatToSelect, table.getTable(), partOfquery));
    List<T> resList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      CollectionType listType =
          mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
      resList = mapper.readValue(jsonFromMySQL, listType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return resList;
  }

  public <T> List<T> getMySQLObjectEquals(String query, Class<T> tClass) {
    String jsonFromMySQL = getSmthToMap(query);
    List<T> resList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      CollectionType listType =
          mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
      resList = mapper.readValue(jsonFromMySQL, listType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return resList;
  }

  public List<String> getStringList(String query) {
    List<String> list = new ArrayList<>();
    ResultSet rs;
    System.out.println(query);
    try {
      rs = MSQLBase.connection.createStatement().executeQuery(query);
      if (rs != null) {
        while (rs.next()) {
          list.add(rs.getString(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}

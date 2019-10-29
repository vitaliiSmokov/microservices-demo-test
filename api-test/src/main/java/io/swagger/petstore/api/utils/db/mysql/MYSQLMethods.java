package io.swagger.petstore.api.utils.db.mysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.swagger.petstore.api.utils.db.mysql.enums.DBTables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MYSQLMethods {

  public void deleteEntry(DBTables tableFrom, String partOfQuery) {
    String query = String.format("DELETE FROM %1$s WHERE %2$s", tableFrom.getTable(), partOfQuery);

    try {
      log.info(">>> " + query);
      new QueryRunner().update(MSQLBase.connection, query);
    } catch (SQLException e) {
      log.error(e.getMessage());
    }
  }

  @Step("Update table {0}")
  public void updateQuery(DBTables tableFrom, String setSmth, String whereQuery) {
    String query =
        String.format(
            "UPDATE %1$s SET %2$s " + "WHERE %3$s", tableFrom.getTable(), setSmth, whereQuery);
    log.info(query);

    try {
      new QueryRunner().update(MSQLBase.connection, query);
    } catch (SQLException e) {
      log.error(e.getMessage());
    }
  }

  public String getSmth(DBTables table, String whatToSelect, String partOfquery) {
    ResultSet rs;
    String query =
        String.format(
            "SELECT %1$s  FROM %2$s WHERE %3$s", whatToSelect, table.getTable(), partOfquery);
    log.info(query);
    try {
      rs = MSQLBase.connection.createStatement().executeQuery(query);
      if (rs.next()) {
        return rs.getString(1);
      }

    } catch (SQLException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  private String getSmthToMap(String query) {
    List list = new ArrayList();
    ResultSet rs;
    log.info(query);
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
      log.error(e.getMessage());
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
      log.error(e.getMessage());
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
      log.error(e.getMessage());
    }
    return resList;
  }

  public List<String> getStringList(String query) {
    List<String> list = new ArrayList<>();
    ResultSet rs;
    log.info(query);
    try {
      rs = MSQLBase.connection.createStatement().executeQuery(query);
      if (rs != null) {
        while (rs.next()) {
          list.add(rs.getString(1));
        }
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    }
    return list;
  }
}

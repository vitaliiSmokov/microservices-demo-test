package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Vitalii Smokov 02.11.2018
 */
public class JsonUtil<T> {

  /**
   * This method parses JSON file into POJO, using DTO class
   *
   * @param file JSON file to be parsed
   * @param dtoBaseClass DTO class that the JSON should be parsed into (needs to be:
   * YourClass.getClass())
   * @return the ArrayList of data transfer objects that were received from the JSON file
   */
  public List<T> parseJsonToList(String file, Class<?> dtoBaseClass) {
    List<T> result = null;
    try {
      InputStream input = new FileInputStream(file);
      ObjectMapper objectMapper = new ObjectMapper();
      TypeFactory typeFactory = TypeFactory.defaultInstance();
      result = objectMapper.readValue(input, typeFactory.constructCollectionType(
          ArrayList.class, dtoBaseClass));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public Map<T, T> parseJsonToMap(String file) {
    Map<T, T> map = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      map = objectMapper.readValue(
//                    new File(file), new TypeReference<Map<String, Object>>() {
          new FileInputStream(file), new TypeReference<Map<String, Object>>() {
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }

  public Class<T> parseJsonToPOJO(String file, Class<?> dtoBaseClass) {
    Class<T> result = null;
    try {
      result = (Class<T>) new ObjectMapper().readValue(new FileInputStream(file), dtoBaseClass);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}

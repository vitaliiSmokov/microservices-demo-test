package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.socks.ui.pojo.Card;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vitalii Smokov 10.12.2018
 */
public class CardUtil {

  private static ObjectMapper mapper = new ObjectMapper();
  private static String pathToJson;

  public static List<Card> getVisaCards() {
    pathToJson = "C:\\Users\\user\\IdeaProjects\\lukspay_tests\\UI_tests\\src\\test\\resources\\";
    List cards = new LinkedList();
    try {
      cards = mapper.readValue(new File(pathToJson.concat("visaCC.json")),
          TypeFactory.defaultInstance()
              .constructCollectionType(
                  ArrayList.class, Card.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return cards;
  }

  public static List<Card> getMasterCards() {
    pathToJson = "C:\\Users\\user\\IdeaProjects\\lukspay_tests\\UI_tests\\src\\test\\resources\\";
    List cards = new LinkedList();
    try {
      cards = mapper.readValue(new File(pathToJson.concat("MasterCardCC.json")),
          TypeFactory.defaultInstance()
              .constructCollectionType(
                  ArrayList.class, Card.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return cards;
  }
}

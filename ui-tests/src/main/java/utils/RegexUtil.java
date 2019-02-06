package utils;

import java.util.Optional;

/**
 * @author Vitalii Smokov 23.01.2019
 */
public class RegexUtil {

  public static String removeSpecialCharactersFrom(String text) {
    return Optional.ofNullable(text)
        .map(text1 -> text1.replaceAll("[^a-zA-Z0-9]", ""))
        .orElse(null);
  }

  public static String removeLettersFrom(String text) {
    return Optional.ofNullable(text)
        .map(str -> str.replaceAll("[^\\d.]", ""))
        .orElse(null);
  }

  public static String removeNumericFrom(String text) {
    return Optional.ofNullable(text)
        .map(str -> str.replaceAll("[^A-Za-z]", ""))
        .orElse(null);
  }

  public static String limitTextLengthTo(String text, int length) {
    return Optional.ofNullable(text)
        .map(str -> str.replaceAll(String.format("\\d{%s}", length), ""))
        .orElse(null);
  }
}

package com.socks.api.utils;

import java.util.*;

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

  public static String toUpperCaseFirstLetter(String text) {
    return text.substring(0,1).toUpperCase().concat(text.substring(1));
  }
}

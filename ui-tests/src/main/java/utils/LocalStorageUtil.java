package utils;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Vitalii Smokov 14.05.2019
 */
public class LocalStorageUtil {

  public static void removeItemFromLocalStorage(String item) {
    executeJavaScript(String.format(
        "window.localStorage.removeItem('%s');", item));
  }

  public static boolean isItemPresentInLocalStorage(String item) {
    return !(executeJavaScript(String.format(
        "return window.localStorage.getItem('%s');", item)) == null);
  }

  public static String getItemFromLocalStorage(String key) {
    return (String) executeJavaScript(String.format(
        "return window.localStorage.getItem('%s');", key));
  }

  public static String getKeyFromLocalStorage(int key) {
    return (String) executeJavaScript(String.format(
        "return window.localStorage.key('%s');", key));
  }

  public static Long getLocalStorageLength() {
    return (Long) executeJavaScript("return window.localStorage.length;");
  }

  public static void setItemInLocalStorage(String item, String value) {
    executeJavaScript(String.format(
        "window.localStorage.setItem('%s','%s');", item, value));
  }

  public static void clearLocalStorage() {
    executeJavaScript("window.localStorage.clear();");
  }
}

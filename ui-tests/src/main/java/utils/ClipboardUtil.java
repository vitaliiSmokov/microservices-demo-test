package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

/**
 * @author Vitalii Smokov 20.02.2019
 */
public class ClipboardUtil {

  public static void pasteDataFromClipboard(SelenideElement element) {
    element.sendKeys(Keys.CONTROL + "v");
  }

  public static void copyDataFromFieldToClipboard(SelenideElement element) {
    element.sendKeys(Keys.CONTROL + "a");
    element.sendKeys(Keys.CONTROL + "c");
  }
}

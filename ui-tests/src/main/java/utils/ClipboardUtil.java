package utils;

import static java.awt.Toolkit.getDefaultToolkit;

import com.codeborne.selenide.SelenideElement;
import java.awt.datatransfer.*;
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

  public static void insertTextIntoSystemClipboard(String text) {
    getSystemClipboard()
        .setContents(new StringSelection(text), null);
  }

  private static Clipboard getSystemClipboard() {
    return getDefaultToolkit().getSystemClipboard();
  }
}

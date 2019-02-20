package utils;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vitalii Smokov 20.02.2019
 */
@Slf4j
public class JsUtil {

  public static void scrollInsideElement(SelenideElement element, String pixels) {
    log.info("Scroll inside the element {}", element.getAttribute("id"));
    Selenide.executeJavaScript(
        String.format("document.querySelector('%s').scrollTop = %s", element, pixels));
  }

  public static void clickElementByJS(SelenideElement element) {
    log.info("Clicking on the element {} by JS", element.getAttribute("id"));
    Selenide.executeJavaScript(
        "arguments[0].click();", element.toWebElement()
    );
  }

  public static void clickElementByJSContainsText(String text) {
    clickElementByJS($x(String.format("//*[contains(text(),'%s')]", text)));
  }
}

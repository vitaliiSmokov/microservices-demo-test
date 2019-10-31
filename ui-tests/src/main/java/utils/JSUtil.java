package utils;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

/**
 * @author Vitalii Smokov 20.02.2019
 */
@Slf4j
public class JSUtil {

  public static void scrollInsideElement(SelenideElement element, String pixels) {
    log.info("Scroll inside the element {}", element.getAttribute("id"));
    executeJavaScript(
        String.format("document.querySelector('%s').scrollTop = %s", element, pixels));
  }

  public static void clickElementByJS(SelenideElement element) {
    log.info("Clicking on the element {} by JS", element.getAttribute("id"));
    executeJavaScript(
        "arguments[0].click();", element.toWebElement()
    );
  }

  public static void clickElementByJSContainsText(String text) {
    clickElementByJS($x(String.format("//*[contains(text(),'%s')]", text)));
  }

  public static void removeElements(ElementsCollection collection){
    for(SelenideElement element : collection) {
      executeJavaScript(
        "arguments[0].remove();", element.toWebElement());
    }
  }
  public static void setElementsAttribute(ElementsCollection collection, String attr, String value){
    for(SelenideElement element : collection) {
      executeJavaScript("arguments[0].setAttribute('" + attr + "', '" + value + "');",
          element.toWebElement());
    }
  }

  public void setElementText(ElementsCollection collection, String text){
    for(SelenideElement element : collection){
      executeJavaScript("arguments[0].innerHTML = \""+text+"\";", element.toWebElement());
      element.waitUntil(Condition.exactText(text), 5000);
//      WebDriverWait wait = new WebDriverWait(driver(), 5);
//      wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
  }

  private SelenideElement highlightElement(SelenideElement element) {
    executeJavaScript("arguments[0].style.border='3px solid red'", element.toWebElement());
    return element;
  }
}

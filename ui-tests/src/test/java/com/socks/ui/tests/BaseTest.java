package com.socks.ui.tests;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import com.socks.ui.pages.enums.Browser;
import io.restassured.RestAssured;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import report.AllureSelenide;
import utils.MyDriverManager;
import utils.PropertyUtil;

/**
 * @author Vitalii Smokov
 */
abstract class BaseTest {

  final static Properties properties = new PropertyUtil()
      .readPropertyFromFile("src/test/resources/test.properties");
  static Faker faker;

  @AfterSuite(alwaysRun = true)
  public static void tearDown() {
    if (WebDriverRunner.hasWebDriverStarted()) {
      WebDriverRunner.getWebDriver().close();
    }
  }

  @BeforeSuite
  public void initBrowser() {
    Browser browser = System.getProperty("selenide.browser") != null
        ? Browser.valueOf(System.getProperty("selenide.browser").toUpperCase()) : Browser.CHROME;
    switch (browser) {
      case FIREFOX: {
        Configuration.browser = browser.toString().toLowerCase();
        break;
      }
      case REMOTE: {
        WebDriverRunner.setWebDriver(MyDriverManager.initFirefoxDriver());
        break;
      }
      default: {
        Configuration.browser = "chrome";
      }
    }

    Configuration.baseUrl = properties.getProperty("link");
    RestAssured.baseURI = properties.getProperty("link").concat("api");
    SelenideLogger.addListener("allure", new AllureSelenide());
    faker = new Faker();
  }

  void waitForJSLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), 30).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
  }

  void waitForAngularLoad() {
    final String angularReadyScript = "return (window.angular !== undefined) && (angular.element(document.body).injector() !== undefined) && (angular.element(document.body).injector().get('$http').pendingRequests.length === 0)";
    ExpectedCondition<Boolean> angularLoad = webDriver -> Boolean
        .valueOf(executeJavaScript(angularReadyScript).toString());
    Wait().until(angularLoad);
  }
}

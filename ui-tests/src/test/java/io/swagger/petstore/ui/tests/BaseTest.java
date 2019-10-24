package io.swagger.petstore.ui.tests;

import static io.swagger.petstore.api.utils.RandomUtil.getFaker;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.swagger.petstore.ui.pages.enums.Browser;
import io.restassured.RestAssured;
import java.util.Properties;

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
    //    new WebDriverManager().setup(new ChromeConfig());
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
    faker = getFaker();
  }
}

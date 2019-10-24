package utils;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.qameta.allure.Step;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Objects;

import io.swagger.petstore.ui.pages.enums.Browser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Vitalii Smokov 18.01.2019
 */
public class MyDriverManager {

  @Step("Init remote selenoid Chrome driver")
  public static WebDriver initChromeDriver() {
    return getRemoteWebDriver(getSelenoidCapabilities(Browser.CHROME, "69.0"));
  }

  @Step("Init remote selenoid Firefox driver")
  public static WebDriver initFirefoxDriver() {
    return getRemoteWebDriver(getSelenoidCapabilities(Browser.FIREFOX, "63.0"));
  }

  @Step("Init remote selenoid Chrome driver with locale: {locale}")
  public static WebDriver initChromeDriverWithLocale(String locale) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--lang=" + locale);
    DesiredCapabilities browser = getSelenoidCapabilities(Browser.CHROME, "70.0");
    browser.setCapability(ChromeOptions.CAPABILITY, options);
    return getRemoteWebDriver(browser);
  }


  private static DesiredCapabilities getSelenoidCapabilities(Browser browser, String version) {
    Configuration.browser = browser.toString().toLowerCase();
    ChromeDriverManager.getInstance().setup();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser.toString().toLowerCase());
    capabilities.setVersion(version);
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", false);
    return capabilities;
  }

  private static WebDriver getRemoteWebDriver(DesiredCapabilities browser) {
    RemoteWebDriver driver = null;
    try {
      driver = new RemoteWebDriver(
          URI.create("http://3.121.85.141:4444/wd/hub").toURL(),
          browser
      );
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    setDimension(driver, new Dimension(1920, 1080));
    return driver;
  }

  private static void setDimension(RemoteWebDriver driver, Dimension dimension) {
    Objects.requireNonNull(driver).manage().window().setSize(dimension);
  }

}
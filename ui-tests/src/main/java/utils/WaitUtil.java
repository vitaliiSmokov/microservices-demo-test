package utils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import lombok.extern.slf4j.*;

/**
 * @author Vitalii Smokov 11.02.2019
 */
@Slf4j
public class WaitUtil {

  public static void waitWhileLoaderIsDisplayed(long timeout) {
    log.info("Waiting while the loader is displayed for up to {} sec", timeout / 1000);
    $(".loader-wrapper")
        .waitWhile(visible, timeout);
  }

  public static void waitWhileLoaderIsDisplayed() {
    waitWhileLoaderIsDisplayed(15000);
  }

}

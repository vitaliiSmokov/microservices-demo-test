package utils;

import com.codeborne.selenide.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import org.openqa.selenium.*;
import org.testng.*;
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.comparison.*;
import ru.yandex.qatools.ashot.coordinates.*;
import ru.yandex.qatools.ashot.shooting.*;

/**
 * @author Vitalii Smokov 11.06.2019
 */

/**
 * Также нужно учесть, что наведение мышью на ссылки/блоки может влиять на внешний вид приложения,
 * поэтому простым способом перед началом наших тестов просто уводим курсор в левый верхний угол
 * экрана: Robot bot = new Robot(); bot.mouseMove(0, 0);
 */
public class ScreenshotUtil {

  private static final AShot aShot = new AShot();

  public static Screenshot takeScreenshotOfPage() {
    return aShot
        .takeScreenshot(WebDriverRunner.getWebDriver());
  }

  public static Screenshot takeScreenshotOfHolePage() {
    return aShot
        .shootingStrategy(ShootingStrategies.viewportPasting(100))
        .takeScreenshot(WebDriverRunner.getWebDriver());
  }

  public static Screenshot takeScreenshotOfHolePageIgnoringElements(Set<By> setIgnoredElements) {
//    Set<By> setIgnoredElements = setIgnoredElements(By.cssSelector(".banner"), By.cssSelector(".userPhoto"));
    return aShot
        .ignoredElements(setIgnoredElements)
        .shootingStrategy(ShootingStrategies.viewportPasting(100))
        .takeScreenshot(WebDriverRunner.getWebDriver());
  }

  public static Screenshot takeScreenshotOfElement(SelenideElement element) {
    return aShot
        .takeScreenshot(WebDriverRunner.getWebDriver(), element.toWebElement());
  }

  /**
   * Важно: по умолчанию для определения координат элемента используется jQuery. Если у вас на
   * проекте отсутствует поддержка jQuery, то нужно указать использование WebDriverCoordsProvider
   * для их определения с помощью WebDriver API:
   */
  public static Screenshot takeScreenshotOfElementViaWebDriverAPI(SelenideElement element) {
    return aShot.coordsProvider(new WebDriverCoordsProvider())
        .takeScreenshot(WebDriverRunner.getWebDriver(), element.toWebElement());
  }

  public static void seveScreenShotToFile(Screenshot screenshot) {
    File actualFile = new File("actualDir+name" + ".png");
    try {
      ImageIO.write(screenshot.getImage(), "png", actualFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Screenshot readScreenshotFromFile(String patToFile) {
    Screenshot actualScreenshot = null;
    try {
      actualScreenshot = new Screenshot(ImageIO.read(new File(patToFile + ".png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
//    expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas());
    return actualScreenshot;
  }

  public static void compareScreenshots(Screenshot expectedScreenshot, Screenshot actualScreenshot) {
    ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  public static void genereteDiffImageWithMarks(ImageDiff diff, String patToFile) {
    File diffFile = new File(patToFile + ".png");
    try {
      ImageIO.write(diff.getMarkedImage(), "png", diffFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

////  https://tproger.ru/articles/animated-gif-java/
//  public static void genereteDiffGifImage(ImageDiff diff, String patToFile) {
//    File[] filesArray = {expectedFile, actualFile, diffFile};
//    gifFile = GifSequenceWriter.createGIF(filesArray, resultGifs+name);
//  }

//  public static void attachImagesToAllure() {
//    //Attach images to report
//    AllureAttachments.attachScreen(expectedFile.getAbsolutePath(), "Expected: "+name);
//    AllureAttachments.attachScreen(actualFile.getAbsolutePath(), "Actual: "+name);
//    AllureAttachments.attachScreen(diffFile.getAbsolutePath(), "Differ: "+name);
//  }
}

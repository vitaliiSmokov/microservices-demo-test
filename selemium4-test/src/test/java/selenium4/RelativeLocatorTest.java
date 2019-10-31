package selenium4;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

@Slf4j
public class RelativeLocatorTest {
    private final String URL = "https://www.swtestacademy.com";
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor javascriptExecutor;

    @BeforeTest
    public void setupTest() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10));
        this.javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void T01_getURLExample() throws InterruptedException {
        driver.get(URL);

        WebElement firstArticle = driver.findElement(By.xpath("(//article)[1]"));
        log.info(
                "First Article Name: "
                        + firstArticle.findElement(By.cssSelector(".entry-title")).getText());
        highlightElement(firstArticle);

        WebElement rightOfFirstArticle =
                driver.findElement(withTagName("article").toRightOf(By.xpath("(//article)[1]")));
        log.info(
                "Right Of First Article Name: "
                        + rightOfFirstArticle.findElement(By.cssSelector(".entry-title")).getText());
        highlightElement(rightOfFirstArticle);

        WebElement belowOfFirstArticle = driver.findElement(withTagName("article").below(firstArticle));
        log.info(
                "Below Of First Article Name: "
                        + belowOfFirstArticle.findElement(By.cssSelector(".entry-title")).getText());
        highlightElement(belowOfFirstArticle);

//        WebElement leftOfFirstArticle =
//                driver.findElement(withTagName("article").toLeftOf(firstArticle));
//
//        log.info(leftOfFirstArticle.findElement(By.cssSelector(".entry-title")).getText());
//        highlightElement(leftOfFirstArticle);
//
//        WebElement aboveOfFirstArticle =
//                driver.findElement(withTagName("article").above(firstArticle));
//        log.info("Above Of First Article Name: " +
//                aboveOfFirstArticle.findElement(By.cssSelector(".entry-title")).getText());
//        highlightElement(aboveOfFirstArticle);
    }

    private WebElement highlightElement(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
        return element;
    }
}

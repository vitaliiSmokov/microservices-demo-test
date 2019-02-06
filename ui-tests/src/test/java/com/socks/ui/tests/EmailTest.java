package com.socks.ui.tests;

import com.codeborne.selenide.WebDriverRunner;
import java.util.Properties;
import javax.mail.Message;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.EmailUtils;
import utils.PropertyUtil;

/**
 * @author Vitalii Smokov 16.01.2019
 */
public class EmailTest {

  private final static Properties properties = new PropertyUtil()
      .readPropertyFromFile("src/test/resources/test.properties");
  private static EmailUtils emailUtils;

  @BeforeClass
  public static void connectToEmail() {
    try {
      emailUtils = new EmailUtils(
          properties.getProperty("gLogin"),
          properties.getProperty("gPwd"),
          "smtp.gmail.com",
          EmailUtils.EmailFolder.INBOX
      );
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false)
  public void testVerificationCode() {
    try {
      //TODO: Execute actions to send verification code to email

      String verificationCode = emailUtils.getAuthorizationCode();

      //TODO: Enter verification code on screen and submit

      //TODO: add assertions

    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false)
  public void testTextContained() {
    try {
      Message email = emailUtils.getMessagesBySubject("Loan Approval", true, 5)[0];
      Assert.assertTrue(emailUtils.isTextInMessage(email, "You have been approved"),
          "Approval message is not in email");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false)
  public void testLink() {

    //TODO: apply for a loan using criteria that will result in the application being rejected

    try {
      Message email = emailUtils.getMessagesBySubject("Decision on Your Loan Application",
          true, 5)[0];
      String link = emailUtils.getUrlsFromMessage(email, "Click here to view the reason").get(0);

      WebDriverRunner.getWebDriver().get(link);

      //TODO: continue testing
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }

}

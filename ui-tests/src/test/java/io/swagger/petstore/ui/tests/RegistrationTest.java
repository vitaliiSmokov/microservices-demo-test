package io.swagger.petstore.ui.tests;

import io.swagger.petstore.ui.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class RegistrationTest extends BaseTest {

  @Test
  public void testCanRegisterNewUser() {
    MainPage.open()
        .registerUser();
  }
}

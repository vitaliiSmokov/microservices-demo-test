package com.socks.ui.tests;

import com.socks.ui.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class RegistrationTest extends BaseTest{

    @Test
    public void testCanRegisterNewUser() {
        MainPage.open()
                .registerUser();
    }
}

package com.socks.ui.tests;

import com.socks.ui.pages.MainPage;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{

    @Test
    public void testCanRegisterNewUser() {
        MainPage.open()
                .registerUser();
    }
}

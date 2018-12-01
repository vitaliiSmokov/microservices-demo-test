package com.socks.ui.tests;

import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Configuration.*;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        browser = "chrome";
        browserSize = "1920x1080";
//        browserSize = "1366x768";
        baseUrl = "http://68.183.67.80";
    }
}

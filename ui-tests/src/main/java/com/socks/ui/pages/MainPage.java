package com.socks.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement registerLink = $("#register > a");
    private SelenideElement loginLink = $("#login > a");
    private SelenideElement loggedUserLabel = $("#howdy > a");

    public static MainPage open() {
        return Selenide.open("/", MainPage.class);
    }

    public void registerUser() {
        registerLink.click();
    }
}

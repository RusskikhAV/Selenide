package com.google.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleMainPage {
    private SelenideElement searchInput = $x("//textarea[@class='gLFyf']");
    private SelenideElement searchButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']");

    public void enterQueryParam(String query) {
        searchInput.shouldBe(Condition.visible).setValue(query);
    }

    public void clickSearchButton() {
        searchButton.shouldBe(Condition.visible).pressEnter();
    }
}

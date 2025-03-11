package com.google.steps;

import com.codeborne.selenide.Selenide;
import com.google.pages.GoogleMainPage;
import io.qameta.allure.Step;

public class GoogleMainPageSteps {
    protected GoogleMainPage googleMainPage;

    public GoogleMainPageSteps() {
        googleMainPage = new GoogleMainPage();
    }

    @Step("Ввести запрос в input поле")
    public GoogleMainPageSteps inputText(String query) {
        googleMainPage.enterQueryParam(query);
        return this;
    }

    @Step("Нажать на кнопку поиска")
    public GoogleMainPageSteps clickSearchButton() {
        googleMainPage.clickSearchButton();
        return this;
    }
}

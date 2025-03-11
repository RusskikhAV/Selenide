package com.google.steps;

import com.codeborne.selenide.Selenide;
import com.google.pages.GoogleMainPage;
import io.qameta.allure.Step;

public class GoogleMainPageSteps {
    protected GoogleMainPage googleMainPage;

    public GoogleMainPageSteps() {
        googleMainPage = new GoogleMainPage();
    }

    @Step("Вводим запрос в поле")
    public GoogleMainPageSteps inputText(String query) {
        googleMainPage.enterQueryParam(query);
        return this;
    }

    @Step("Нажимаем на кнопку поиска")
    public GoogleMainPageSteps clickSearchButton() {
        googleMainPage.clickSearchButton();
        return this;
    }
}

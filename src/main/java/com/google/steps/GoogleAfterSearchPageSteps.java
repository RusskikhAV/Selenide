package com.google.steps;

import com.google.pages.GoogleAfterSearchPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


public class GoogleAfterSearchPageSteps {
    GoogleAfterSearchPage googleAfterSearchPage;

    public GoogleAfterSearchPageSteps() {
        googleAfterSearchPage = new GoogleAfterSearchPage();
    }

    @Step("Проверяем заголовки h1")
    public GoogleAfterSearchPageSteps checkH3(String expectedText) {
        Assertions.assertTrue(googleAfterSearchPage.returnAllH3FromPage().contains(expectedText));
        return this;
    }

}

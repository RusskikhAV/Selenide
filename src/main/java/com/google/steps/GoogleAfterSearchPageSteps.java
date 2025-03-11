package com.google.steps;

import com.google.pages.GoogleAfterSearchPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;


public class GoogleAfterSearchPageSteps {
    GoogleAfterSearchPage googleAfterSearchPage;

    public GoogleAfterSearchPageSteps() {
        googleAfterSearchPage = new GoogleAfterSearchPage();
    }

    @Step("Проверяем заголовки h3")
    public GoogleAfterSearchPageSteps checkTitles(int count, String expectedText) {
        List<String> titles = googleAfterSearchPage.getFirstNTitles(count);
        for (String title : titles) {
            Assertions.assertTrue(
                    title.contains(expectedText),
                    "Заголовок " + title + " не содержит " + expectedText
            );
        }
        return this;
    }

}

package com.google;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class GoogleSearchTest extends BaseUiTest {
    public final static String BASE_URL = "https://www.google.com";
    public final static String SEARCH_STRING = "Selenide Java";
    public final static String CONTAINS_STRING = "Selenide";
    public final static int TITLES_COUNT = 10;

    {
        open(BASE_URL);
    }

    @Test
    @Description("")
    public void searchWithQueryParam() {
        googleMainPageSteps
                .inputText(SEARCH_STRING)
                .clickSearchButton();
        googleAfterSearchPageSteps
                .checkTitles(TITLES_COUNT, CONTAINS_STRING);
    }
}

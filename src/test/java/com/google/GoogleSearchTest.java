package com.google;

import org.junit.jupiter.api.Test;

public class GoogleSearchTest extends BaseUiTest {
    public final static String BASE_URL = "https://www.google.com";
    public final static String SEARCH_STRING = "Selenide Java";
    public final static String CONTAINS_STRING = "Selenide";

    {
        open(BASE_URL);
    }

    @Test
    public void searchWithQueryParam() {
        googleMainPageSteps
                .inputText(SEARCH_STRING)
                .clickSearchButton();
        googleAfterSearchPageSteps
                .checkH3(CONTAINS_STRING);
    }

}

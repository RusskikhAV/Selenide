package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.steps.GoogleAfterSearchPageSteps;
import com.google.steps.GoogleMainPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    protected GoogleMainPageSteps googleMainPageSteps;
    protected GoogleAfterSearchPageSteps googleAfterSearchPageSteps;

    public BaseUiTest() {
        refreshPages();
    }

    private void refreshPages() {
        googleMainPageSteps = new GoogleMainPageSteps();
        googleAfterSearchPageSteps = new GoogleAfterSearchPageSteps();
    }

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.headless = false;
        Configuration.browser = "chrome";
        Configuration.browserSize = "2560x1440";
        Configuration.timeout = 60000;
    }

    protected void open(String pageUrl) {
        Selenide.open(pageUrl);
        //Selenide.sleep(3000);
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}

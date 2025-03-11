package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.steps.GoogleAfterSearchPageSteps;
import com.google.steps.GoogleMainPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUiTest {
    protected GoogleMainPageSteps googleMainPageSteps;
    protected GoogleAfterSearchPageSteps googleAfterSearchPageSteps;

    private void refreshPages() {
        googleMainPageSteps = new GoogleMainPageSteps();
        googleAfterSearchPageSteps = new GoogleAfterSearchPageSteps();
    }

    protected void open(String pageUrl) {
        Selenide.open(pageUrl);
        Selenide.sleep(1000);
    }

    @BeforeAll
    public static void setUpAll() {
        //WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        System.out.println(Configuration.headless);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
        Configuration.browserCapabilities = options;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 100000;
        System.out.println(Configuration.headless);
        refreshPages();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}

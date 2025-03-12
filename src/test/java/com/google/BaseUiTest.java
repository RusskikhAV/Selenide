package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.steps.GoogleMainPageSteps;
import com.google.steps.GoogleResultImagePageSteps;
import com.google.steps.GoogleResultSearchPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Базовый класс для UI-тестов, предоставляющий общие настройки и методы для работы с браузером.
 * Этот класс инициализирует WebDriver, настраивает браузер и предоставляет методы для открытия страниц и управления тестами.
 */
public class BaseUiTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseUiTest.class);

    /**
     * Шаги для работы с главной страницей Google.
     */
    protected final GoogleMainPageSteps googleMainPageSteps;

    /**
     * Шаги для работы со страницей результатов поиска Google.
     */
    protected final GoogleResultSearchPageSteps googleResultSearchPageSteps;

    /**
     * Шаги для работы со страницей результатов поиска изображений Google.
     */
    protected final GoogleResultImagePageSteps googleResultImagePageSteps;

    /**
     * Конструктор базового класса. Инициализирует шаги для работы с главной страницей, страницей результатов поиска
     * и страницей выдачи изображений.
     */
    public BaseUiTest() {
        this.googleMainPageSteps = new GoogleMainPageSteps();
        this.googleResultSearchPageSteps = new GoogleResultSearchPageSteps();
        this.googleResultImagePageSteps = new GoogleResultImagePageSteps();
    }

    /**
     * Открывает указанный URL в браузере и ожидает загрузки страницы.
     *
     * @param pageUrl URL страницы, которую нужно открыть.
     */
    protected void openPage(String pageUrl) {
        logger.info("Открываем страницу: {}", pageUrl);
        Selenide.open(pageUrl);
        $("body").shouldBe(visible); // Ожидаем загрузки страницы
    }

    /**
     * Настройка браузера перед всеми тестами.
     * Инициализирует WebDriver и настраивает параметры браузера.
     */
    @BeforeAll
    public static void globalSetup() {
        logger.info("Настройка браузера перед всеми тестами");
        WebDriverManager.edgedriver().setup(); // Устанавливаем EdgeDriver
        Configuration.browser = "edge"; // Используем браузер Edge
        Configuration.headless = false; // Отключаем headless-режим
        Configuration.timeout = 60000; // Устанавливаем таймаут ожидания элементов
        Configuration.holdBrowserOpen = false; // Закрывать браузер после тестов
    }

    /**
     * Настройка перед каждым тестом.
     * Инициализирует шаги для работы с главной страницей и страницей результатов поиска.
     */
    @BeforeEach
    public void setup() {
        logger.info("Настройка перед каждым тестом");
    }

    /**
     * Завершение работы после каждого теста.
     * Закрывает браузер и завершает сессию WebDriver.
     */
    @AfterEach
    public void tearDown() {
        logger.info("Завершение работы после теста");
        Selenide.closeWebDriver(); // Закрываем браузер
    }
}
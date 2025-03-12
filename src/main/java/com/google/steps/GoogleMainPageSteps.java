package com.google.steps;

import com.codeborne.selenide.Selenide;
import com.google.pages.GoogleMainPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Класс, представляющий шаги для взаимодействия с главной страницей Google.
 * Использует объект {@link GoogleMainPage} для выполнения действий на странице.
 */
public class GoogleMainPageSteps {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMainPageSteps.class);

    /**
     * Объект страницы GoogleMainPage для взаимодействия с элементами.
     */
    protected GoogleMainPage googleMainPage;

    /**
     * Конструктор класса. Инициализирует объект страницы GoogleMainPage.
     */
    public GoogleMainPageSteps() {
        googleMainPage = new GoogleMainPage();
    }

    /**
     * Вводит поисковый запрос в поле поиска.
     *
     * @param query Поисковый запрос, который нужно ввести.
     * @return Текущий объект GoogleMainPageSteps для цепочки вызовов.
     */
    @Step("Вводим запрос в поле поиска")
    public GoogleMainPageSteps enterSearchQuery(String query) {
        logger.info("Вводим запрос в поле поиска: {}", query);
        googleMainPage.enterSearchQuery(query);
        return this;
    }

    /**
     * Нажимает кнопку поиска.
     *
     * @return Текущий объект GoogleMainPageSteps для цепочки вызовов.
     */
    @Step("Нажимаем на кнопку поиска")
    public GoogleMainPageSteps clickSearchButton() {
        logger.info("Нажимаем на кнопку поиска");
        googleMainPage.clickSearchButton();
        return this;
    }

    /**
     * Нажимает кнопку "Настройки".
     * Добавлена пауза для обработки CAPTCHA (временное решение).
     *
     * @return Текущий объект GoogleMainPageSteps для цепочки вызовов.
     */
    @Step("Нажимаем на кнопку 'Настройки'")
    public GoogleMainPageSteps clickSettingsButton() {
        Selenide.sleep(10000); // Временная пауза для обработки CAPTCHA
        logger.info("Нажимаем на кнопку 'Настройки'");
        googleMainPage.clickSettingsButton();
        return this;
    }

    /**
     * Проверяет, что все элементы выпадающего списка настроек отображаются.
     *
     * @return Текущий объект GoogleMainPageSteps для цепочки вызовов.
     * @throws AssertionError если хотя бы один элемент не отображается.
     */
    @Step("Проверяем, что выпадающий список меню отображается")
    public GoogleMainPageSteps verifyMenuIsVisible() {
        Selenide.sleep(2000); // Временная пауза для загрузки элементов
        logger.info("Проверяем, что выпадающий список меню отображается");

        List<Boolean> isMenuVisible = googleMainPage.verifySettingsDropdownIsVisible();
        for (boolean isVisible : isMenuVisible) {
            Assertions.assertTrue(isVisible, "Элемент выпадающего списка не отображается");
        }
        return this;
    }
}

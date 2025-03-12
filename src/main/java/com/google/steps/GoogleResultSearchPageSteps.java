package com.google.steps;

import com.codeborne.selenide.Selenide;
import com.google.pages.GoogleResultSearchPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Класс для выполнения шагов на странице результатов поиска Google.
 */
public class GoogleResultSearchPageSteps {

    private static final Logger logger = LoggerFactory.getLogger(GoogleResultSearchPageSteps.class);
    private final GoogleResultSearchPage googleResultSearchPage;

    /**
     * Конструктор класса. Инициализирует страницу результатов поиска Google.
     */
    public GoogleResultSearchPageSteps() {
        this.googleResultSearchPage = new GoogleResultSearchPage();
    }

    /**
     * Проверяет, что заголовки h3 на странице результатов поиска содержат ожидаемый текст.
     *
     * @param count         количество заголовков для проверки
     * @param expectedText  ожидаемый текст, который должен содержаться в заголовках
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Проверяем заголовки h3")
    public GoogleResultSearchPageSteps verifyTitlesContainText(int count, String expectedText) {
        logger.info("Проверяем заголовки h3 на наличие текста: {}", expectedText);
        List<String> titles = googleResultSearchPage.getFirstNTitles(count);
        for (String title : titles) {
            Assertions.assertTrue(
                    title.contains(expectedText),
                    "Заголовок " + title + " не содержит " + expectedText
            );
        }
        return this;
    }

    /**
     * Переходит на следующую страницу результатов поиска.
     *
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Переходим на следующую страницу")
    public GoogleResultSearchPageSteps navigateToNextPage() {
        logger.info("Переходим на следующую страницу");
        googleResultSearchPage.goToNextPage();
        return this;
    }

    /**
     * Проверяет информацию о текущей странице.
     *
     * @param expectedPageInfo ожидаемая информация о текущей странице
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Проверяем информацию о текущей странице")
    public GoogleResultSearchPageSteps verifyCurrentPageInfo(String expectedPageInfo) {
        Selenide.sleep(2000);
        logger.info("Проверяем информацию о текущей странице");
        String[] pageInfoParts = googleResultSearchPage.getCurrentPageInfo().split(" ");
        Assertions.assertEquals(pageInfoParts[4], expectedPageInfo);
        return this;
    }

    /**
     * Проверяет, что на странице присутствует поле поиска.
     *
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Проверяем, что на странице присутствует поле поиска")
    public GoogleResultSearchPageSteps verifySearchInputFieldIsDisplayed() {
        logger.info("Проверяем, что на странице присутствует поле поиска");
        Assertions.assertTrue(googleResultSearchPage.isSearchInputFieldDisplayed());
        return this;
    }

    /**
     * Проверяет, что на странице есть кнопка 'Поиск в Google'.
     *
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Проверяем, что на странице есть кнопка 'Поиск в Google'")
    public GoogleResultSearchPageSteps verifySearchButtonIsDisplayed() {
        logger.info("Проверяем, что на странице есть кнопка 'Поиск в Google'");
        Assertions.assertTrue(googleResultSearchPage.isSearchButtonDisplayed());
        return this;
    }

    /**
     * Проверяет, что внизу страницы есть элементы навигации.
     *
     * @return текущий экземпляр класса для цепочки вызовов
     */
    @Step("Проверяем, что внизу страницы есть элементы навигации")
    public GoogleResultSearchPageSteps verifyPaginationElementsAreDisplayed() {
        logger.info("Проверяем, что внизу страницы есть элементы навигации");
        boolean allPagesValid = googleResultSearchPage.getPaginationElementsText()
                .stream()
                .allMatch(id -> id.startsWith("Page"));

        Assertions.assertTrue(allPagesValid, "Не все страницы начинаются с 'Page'");
        return this;
    }
}

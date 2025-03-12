package com.google;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования функциональности поиска в Google.
 * Включает тесты для проверки поиска, пагинации, изображений и UI-элементов на странице результатов.
 */
public class GoogleSearchTest extends BaseUiTest {

    /**
     * Базовый URL для Google.
     */
    public static final String BASE_URL = "https://www.google.com";

    /**
     * Строка для поиска.
     */
    public static final String SEARCH_QUERY = "Selenide Java";

    /**
     * Ожидаемая строка, которая должна содержаться в результатах поиска.
     */
    public static final String EXPECTED_TEXT_IN_RESULTS = "Selenide";

    /**
     * Количество заголовков на первой странице результатов поиска.
     */
    public static final int EXPECTED_TITLES_COUNT = 8;

    /**
     * Информация о текущей странице (например, "2" для второй страницы).
     */
    public static final String CURRENT_PAGE_INFO = "2";

    /**
     * Открывает базовую страницу Google перед выполнением тестов.
     */
    {
        openPage(BASE_URL);
    }

    /**
     * Тест для проверки поиска с использованием строки запроса.
     * Проверяет, что результаты поиска содержат ожидаемый текст и количество заголовков соответствует ожидаемому.
     */
    @Test
    @Description("Проверка поиска с использованием строки запроса")
    public void testSearchWithQuery() {
        googleMainPageSteps
                .enterSearchQuery(SEARCH_QUERY)
                .clickSearchButton();

        googleResultSearchPageSteps
                .verifyTitlesContainText(EXPECTED_TITLES_COUNT, EXPECTED_TEXT_IN_RESULTS);
    }

    /**
     * Тест для проверки пагинации на странице результатов поиска.
     * Переходит на вторую страницу и проверяет, что результаты соответствуют ожидаемым.
     */
    @Test
    @Description("Проверка пагинации на странице результатов поиска")
    public void testPaginationOnSearchResults() {
        googleMainPageSteps
                .enterSearchQuery(SEARCH_QUERY)
                .clickSearchButton();

        googleResultSearchPageSteps
                .navigateToNextPage()
                .verifyCurrentPageInfo(CURRENT_PAGE_INFO)
                .verifyTitlesContainText(EXPECTED_TITLES_COUNT, EXPECTED_TEXT_IN_RESULTS);
    }

    /**
     * Тест для проверки изображений на странице результатов поиска.
     * Переходит на страницу с изображениями и проверяет их наличие и ширину.
     */
    @Test
    @Description("Проверка изображений на странице результатов поиска")
    public void testImagesOnSearchPage() {
        googleMainPageSteps
                .enterSearchQuery(SEARCH_QUERY)
                .clickSearchButton();

        googleResultImagePageSteps
                .navigateToImagePage()
                .verifyAllImagesAreDisplayed()
                .verifyImageWidths();
    }

    /**
     * Тест для проверки отображения ключевых UI-элементов на странице результатов поиска.
     * Проверяет наличие поля поиска, кнопки поиска и элементов пагинации.
     */
    @Test
    @Description("Проверка отображения ключевых UI-элементов на странице результатов поиска")
    public void testUIElementsOnSearchPage() {
        googleMainPageSteps
                .clickSettingsButton()
                .verifyMenuIsVisible()
                .enterSearchQuery(SEARCH_QUERY)
                .clickSearchButton();

        googleResultSearchPageSteps
                .verifySearchInputFieldIsDisplayed()
                .verifySearchButtonIsDisplayed()
                .verifyPaginationElementsAreDisplayed();
    }
}

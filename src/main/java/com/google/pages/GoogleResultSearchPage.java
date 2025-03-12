package com.google.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для работы со страницей результатов поиска Google.
 * Предоставляет методы для взаимодействия с элементами на странице результатов:
 * - Получение заголовков результатов.
 * - Переход на следующую страницу.
 * - Проверка элементов пагинации и поиска.
 */
public class GoogleResultSearchPage {

    /**
     * Коллекция заголовков результатов поиска (элементы h3).
     */
    private final ElementsCollection searchResultTitles = $$x("//span/a/h3[contains(text(), 'Selenide')]");

    /**
     * Кнопка перехода на вторую страницу результатов.
     */
    private final SelenideElement nextPageButton = $x("//a[@aria-label='Page 2']");

    /**
     * Элемент, отображающий информацию о текущей странице.
     */
    private final SelenideElement currentPageInfo = $x("//div[@id='result-stats']");

    /**
     * Поле ввода поискового запроса.
     */
    private final SelenideElement searchInputField = $x("//textarea[@class='gLFyf']");

    /**
     * Кнопка поиска.
     */
    private final SelenideElement searchSubmitButton = $x("//button[@type='submit']");

    /**
     * Коллекция элементов пагинации.
     */
    private final ElementsCollection paginationElements = $$x("//td[@class='NKTSme']//a");

    /**
     * Возвращает тексты первых {@code count} заголовков результатов поиска.
     *
     * @param count количество заголовков, которые нужно вернуть.
     * @return список текстов заголовков.
     * @throws IllegalArgumentException если запрошенное количество заголовков превышает доступное.
     */
    public List<String> getFirstNTitles(int count) {
        // Ожидание появления хотя бы одного заголовка
        searchResultTitles.shouldHave(sizeGreaterThan(0));

        if (count > searchResultTitles.size()) {
            throw new IllegalArgumentException(
                    "Запрошенное количество " + count + " заголовков превышает доступное " + searchResultTitles.size()
            );
        }

        // Возвращаем список из первых 'count' текстов
        return searchResultTitles.stream()
                .limit(count)
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Переходит на следующую страницу результатов поиска.
     * Добавлена пауза для обработки CAPTCHA (временное решение).
     */
    public void goToNextPage() {
        Selenide.sleep(5000); // Временная пауза для обработки CAPTCHA
        nextPageButton.shouldBe(Condition.visible).scrollIntoView(true).click();
    }

    /**
     * Получает информацию о текущей странице результатов поиска.
     *
     * @return текст, содержащий информацию о текущей странице.
     */
    public String getCurrentPageInfo() {
        return currentPageInfo.innerText();
    }

    /**
     * Проверяет, отображается ли поле ввода поискового запроса.
     *
     * @return true, если поле ввода отображается, иначе false.
     */
    public boolean isSearchInputFieldDisplayed() {
        return searchInputField.shouldBe(Condition.visible).isDisplayed();
    }

    /**
     * Проверяет, отображается ли кнопка поиска.
     *
     * @return true, если кнопка поиска отображается, иначе false.
     */
    public boolean isSearchButtonDisplayed() {
        return searchSubmitButton.shouldBe(Condition.visible).isDisplayed();
    }

    /**
     * Получает список текстов элементов пагинации.
     *
     * @return список текстов элементов пагинации.
     */
    public List<String> getPaginationElementsText() {
        Selenide.sleep(2000); // Временная пауза для загрузки элементов
        return paginationElements.stream()
                .map(element -> element.getAttribute("aria-label"))
                .collect(Collectors.toList());
    }
}

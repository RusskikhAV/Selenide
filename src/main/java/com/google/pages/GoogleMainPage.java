package com.google.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для работы с главной страницей Google.
 * Предоставляет методы для взаимодействия с элементами на главной странице:
 * - Ввод поискового запроса.
 * - Нажатие кнопки поиска.
 * - Взаимодействие с настройками.
 */
public class GoogleMainPage {

    /**
     * Поле ввода поискового запроса.
     */
    private final SelenideElement searchInput = $x("//textarea[@class='gLFyf']");

    /**
     * Кнопка поиска.
     */
    private final SelenideElement searchButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']");

    /**
     * Кнопка "Настройки".
     */
    private final SelenideElement settingsButton = $x("//div[contains(text(), 'Настройки')]");

    /**
     * Выпадающий список настроек.
     */
    private final ElementsCollection settingsDropdownItems = $$x("//g-menu-item");

    /**
     * Вводит поисковый запрос в поле ввода.
     *
     * @param query Поисковый запрос, который нужно ввести.
     */
    public void enterSearchQuery(String query) {
        searchInput.shouldBe(Condition.visible).setValue(query);
    }

    /**
     * Нажимает кнопку поиска.
     */
    public void clickSearchButton() {
        searchButton.shouldBe(Condition.visible).pressEnter();
    }

    /**
     * Нажимает кнопку "Настройки".
     */
    public void clickSettingsButton() {
        settingsButton.shouldBe(Condition.visible).click();
    }

    /**
     * Проверяет, отображаются ли элементы выпадающего списка настроек.
     *
     * @return Список булевых значений, где каждое значение указывает, отображается ли соответствующий элемент.
     */
    public List<Boolean> verifySettingsDropdownIsVisible() {
        return settingsDropdownItems.stream()
                .map(SelenideElement::isDisplayed)
                .toList();
    }
}

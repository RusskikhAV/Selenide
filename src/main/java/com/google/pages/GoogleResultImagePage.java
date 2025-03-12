package com.google.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для работы со страницей изображений Google.
 * Предоставляет методы для взаимодействия с изображениями на странице результатов поиска:
 * - Переход на страницу изображений.
 * - Получение информации об изображениях.
 * - Взаимодействие с изображениями (клик, получение размеров).
 */
public class GoogleResultImagePage {

    /**
     * Элемент для перехода на страницу изображений.
     */
    private final SelenideElement imagesPageLink = $x("//div[contains(text(), 'Картинки')]");

    /**
     * Коллекция всех изображений на странице.
     */
    private final ElementsCollection allImages = $$x("//g-img/img");

    /**
     * Первое изображение на странице.
     */
    private final ElementsCollection firstImage = $$x("//img[@class='YQ4gaf'][1]");

    /**
     * Увеличенное изображение после клика.
     */
    private final SelenideElement expandedImage = $x("//img[@jsname='kn3ccd']");

    /**
     * Переходит на страницу изображений.
     * Добавлена пауза для обработки CAPTCHA (временное решение).
     */
    public void navigateToImagePage() {
        Selenide.sleep(5000); // Временная пауза для обработки CAPTCHA
        imagesPageLink.click();
    }

    /**
     * Получает список идентификаторов всех изображений на странице.
     *
     * @return Список идентификаторов изображений.
     */
    public List<String> getAllImageIds() {
        Selenide.sleep(2000); // Временная пауза для загрузки изображений
        return allImages.stream()
                .map(image -> image.getAttribute("id"))
                .toList();
    }

    /**
     * Получает ширину первого изображения до клика.
     *
     * @return Ширина первого изображения в пикселях.
     */
    public int getFirstImageWidthBeforeClick() {
        return Integer.parseInt(firstImage.first().getAttribute("width"));
    }

    /**
     * Кликает на первое изображение с помощью JavaScript.
     */
    public void clickFirstImage() {
        Selenide.executeJavaScript("arguments[0].click();", firstImage.first().getWrappedElement());
    }

    /**
     * Получает ширину увеличенного изображения после клика.
     *
     * @return Ширина увеличенного изображения в пикселях.
     */
    public int getExpandedImageWidthAfterClick() {
        clickFirstImage();
        return Integer.parseInt(expandedImage.getAttribute("width"));
    }
}
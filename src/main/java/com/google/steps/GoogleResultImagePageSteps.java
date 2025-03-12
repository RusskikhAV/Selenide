package com.google.steps;

import com.codeborne.selenide.Selenide;
import com.google.pages.GoogleResultImagePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, представляющий шаги для взаимодействия со страницей изображений Google.
 * Использует объект {@link GoogleResultImagePage} для выполнения действий на странице.
 */
public class GoogleResultImagePageSteps {

    private static final Logger logger = LoggerFactory.getLogger(GoogleResultImagePageSteps.class);

    /**
     * Объект страницы GoogleResultImagePage для взаимодействия с элементами.
     */
    private final GoogleResultImagePage googleResultImagePage;

    /**
     * Конструктор класса. Инициализирует объект страницы GoogleResultImagePage.
     */
    public GoogleResultImagePageSteps() {
        googleResultImagePage = new GoogleResultImagePage();
    }

    /**
     * Переходит на страницу изображений.
     *
     * @return Текущий объект GoogleResultImagePageSteps для цепочки вызовов.
     */
    @Step("Переходим на страницу изображений")
    public GoogleResultImagePageSteps navigateToImagePage() {
        logger.info("Переходим на страницу изображений");
        googleResultImagePage.navigateToImagePage();
        return this;
    }

    /**
     * Проверяет, что все изображения на странице имеют идентификаторы, начинающиеся с 'dimg'.
     *
     * @return Текущий объект GoogleResultImagePageSteps для цепочки вызовов.
     * @throws AssertionError если хотя бы одно изображение не соответствует условию.
     */
    @Step("Проверяем, что id всех картинок начинается с 'dimg'")
    public GoogleResultImagePageSteps verifyAllImagesAreDisplayed() {
        logger.info("Проверяем, что id всех картинок начинается с 'dimg'");

        boolean allImagesValid = googleResultImagePage.getAllImageIds()
                .stream()
                .allMatch(id -> id.startsWith("dimg"));

        Assertions.assertTrue(allImagesValid, "Не все id картинок начинаются с 'dimg'");
        return this;
    }

    /**
     * Проверяет, что ширина изображения увеличивается после клика.
     *
     * @return Текущий объект GoogleResultImagePageSteps для цепочки вызовов.
     * @throws AssertionError если ширина изображения не увеличилась.
     */
    @Step("Проверяем, что изображение до клика меньшей ширины, чем после")
    public GoogleResultImagePageSteps verifyImageWidths() {
        logger.info("Проверяем, что изображение до клика меньшей ширины, чем после");

        Selenide.sleep(2000); // Временная пауза для загрузки изображений

        int widthBeforeClick = googleResultImagePage.getFirstImageWidthBeforeClick();
        int widthAfterClick = googleResultImagePage.getExpandedImageWidthAfterClick();

        logger.info("Ширина изображения до клика: {}", widthBeforeClick);
        logger.info("Ширина изображения после клика: {}", widthAfterClick);

        Assertions.assertTrue(
                widthBeforeClick < widthAfterClick,
                "Ширина изображения не увеличилась после клика"
        );
        return this;
    }

}

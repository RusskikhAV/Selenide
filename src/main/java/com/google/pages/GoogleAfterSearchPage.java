package com.google.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Страница результатов запроса поиска
 */
public class GoogleAfterSearchPage {
    private final ElementsCollection h3 = $$x("//h3");

    /**
     * Возвращает тексты первых {@code count} заголовков (элементов h3) на странице.
     *
     * @param count количество заголовков, которые нужно вернуть.
     * @return список текстов заголовков.
     */
    public List<String> getFirstNTitles(int count) {
        Selenide.sleep(10000); // 10 секунд для ввода капчи

        if (count > h3.size()) {
            throw new IllegalArgumentException("Запрошенное количество заголовков превышает доступное");
        }

        return h3.stream()
                .limit(count)
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }
}

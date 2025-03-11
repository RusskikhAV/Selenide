package com.google.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class GoogleAfterSearchPage {
    private ElementsCollection h3 = $$x("//h3");

    public String returnAllH3FromPage() {
        return h3.first().getText();
    }
}

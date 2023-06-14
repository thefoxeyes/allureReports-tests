package com.ezopikhinaelena;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    @Step("Открыть главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Найти репозиторий {REPOSITORY}")
    public void searchForRepository(String REPOSITORY) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").pressEnter();
    }

    @Step("Перейти в репозиторий {REPOSITORY}")
    public void goToRepository(String REPOSITORY) {
        $(linkText(REPOSITORY)).click();
    }

    @Step("Открыть раздел Issues")
    public void openIssuesTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Проверить наличие Issue с номером {number}")
    public void shouldSeeIssueWithNumber(Integer number) {
        $(byText("#" + number)).shouldHave(text("#" + number));
    }
}


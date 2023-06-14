package com.ezopikhinaelena;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class IssueSteps {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = String.valueOf(true);
    }

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static Integer issueNumber = 1;

    @Test
    @Owner("ezopikhinaelena")
    @Feature("Issue")
    @Story("Поиск по номеру Issue")
    @Link(name = "Главная страница", url = "https://github.com")
    @DisplayName("Раздел Issues. Issue #1 есть в списке")
    @Severity(SeverityLevel.BLOCKER)
    public void pureSelenideTests() {
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").pressEnter();
        $(partialLinkText("Issues")).click();
        $(byText("#" + issueNumber)).shouldHave(text("#" + issueNumber));
    }

    @Test
    @Owner("ezopikhinaelena")
    @Feature("Issue")
    @Story("Поиск по номеру Issue")
    @Link(name = "Главная страница", url = "https://github.com")
    @DisplayName("Раздел Issues. Issue #1 есть в списке")
    @Severity(value = SeverityLevel.MINOR)
    public void ConsistingOfLambdaSteps() {
        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });

        step("Найти репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").pressEnter();
        });
        step("Перейти в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открыть раздел Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверить наличие Issue с номером " + issueNumber, () -> {
            $(byText("#" + issueNumber)).shouldHave(text("#" + issueNumber));
        });
    }

    @Test
    @Owner("ezopikhinaelena")
    @Feature("Issue")
    @Story("Поиск по номеру Issue")
    @Link(name = "Главная страница", url = "https://github.com")
    @DisplayName("Раздел Issues. Issue #1 есть в списке")
    @Severity(SeverityLevel.CRITICAL)
    public void annotationTests() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(issueNumber);
    }
    @AfterAll
    static void afterAll() {
        Configuration.holdBrowserOpen = true;
    }
}
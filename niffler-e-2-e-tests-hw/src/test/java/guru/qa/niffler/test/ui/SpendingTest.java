package guru.qa.niffler.test.ui;

import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SpendingTest extends BaseWebTest {

    @BeforeEach
    void doLogin() {
        open(MainPage.URL, LoginPage.class)
                .doLogin("yaro", "secret")
                .checkThatPageLoaded();
    }

    @Category(
            username = "yaro",
            category = "cat2")
    @Spend(
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB
    )
    @Test
    void spendingShouldBeDeletedAfterTableDeleteAction(SpendJson spendJson) {
         new MainPage()
                .selectSpendingByDescription(spendJson.description())
                .clickDeleteSelected()
                .verifySpendingTableIsEmpty();
    }

}

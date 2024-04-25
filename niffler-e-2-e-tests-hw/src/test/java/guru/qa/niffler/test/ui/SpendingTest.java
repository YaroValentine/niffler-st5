package guru.qa.niffler.test.ui;

import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.jupiter.extension.CategoryExtension;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith({
        CategoryExtension.class,
        SpendExtension.class
})
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
            username = "yaro",
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB,
            category = "cat2")
    @Test
    void spendingShouldBeDeletedAfterTableDeleteAction(SpendJson spendJson) {
        open(MainPage.URL, MainPage.class)
                .selectSpendingByDescription(spendJson.description())
                .clickDeleteSelected()
                .verifySpendingTableIsEmpty();
    }

}

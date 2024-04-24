package guru.qa.niffler.test.ui;

import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(SpendExtension.class)
public class SpendingTest extends BaseWebTest {

    @BeforeEach
    void doLogin() {
        open(MainPage.URL, LoginPage.class)
                .doLogin("yaro", "secret")
                .checkThatPageLoaded();
    }

    @Spend(username = "yaro",
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB,
            category = "Обучение")
    @Test
    void spendingShouldBeDeletedAfterTableDeleteAction(SpendJson spendJson) {
        open(MainPage.URL, MainPage.class)
                .selectSpendingByDescription(spendJson.description())
                .clickDeleteSelected()
                .verifySpendingTableIsEmpty();
    }

}

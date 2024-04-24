package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@ExtendWith(SpendExtension.class)
public class SpendingTest {

    static {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void doLogin() {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue("yaro");
        $("input[name='password']").setValue("secret");
        $("button[type='submit']").click();
    }

    @Spend(username = "yaro",
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB,
            category = "Обучение")
    @Test
    void spendingShouldBeDeletedAfterTableDeleteAction(SpendJson spendJson) {
        SelenideElement rowWithSpending = $(".spendings-table tbody")
                .$$("tr")
                .find(text(spendJson.description()));

        rowWithSpending.$$("td").first().scrollTo().click();
        $(".spendings__bulk-actions button").click();

        $(".spendings-table tbody").$$("tr")
                .shouldHave(size(0));
    }


}

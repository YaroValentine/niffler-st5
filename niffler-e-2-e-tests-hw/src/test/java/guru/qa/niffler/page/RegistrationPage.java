package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage<RegistrationPage> {

    public static final String URL = Config.getConfig().getAuthUrl() + "/register";

    private final SelenideElement
            header = $(".form__paragraph"),
            usernameInput = $("#username"),
            passwordInput = $("#password"),
            passwordSubmitInput = $("#passwordSubmit"),
            signUpBtn = $("button[type='submit']"),
            formError = $(".form__error");

    @Override
    public RegistrationPage checkThatPageLoaded() {
        header.shouldHave(text("Registration form"));
        return this;
    }

    public RegistrationPage fillRegistrationForm(String username, String password, String passwordSubmit) {
        usernameInput.val(username);
        passwordInput.val(password);
        passwordSubmitInput.val(passwordSubmit);
        signUpBtn.click();
        return this;
    }

    public RegistrationPage checkErrorMessage(String expectedMessage) {
        formError.shouldHave(text(expectedMessage));
        return this;
    }
}

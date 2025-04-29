package org.example.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.enums.UserCredentials;
import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginBasePage extends BasePage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private ExtendedWebElement errorMessage;

    public LoginBasePage(WebDriver driver) {
        super(driver);
    }

    public InventoryBasePage login(UserCredentials user) {
        return login(user.getUsername(), user.getPassword());
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isElementPresent();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public InventoryBasePage login(String username, String password) {
        usernameField.type(username);
        passwordField.type(password);
        loginButton.click();
        return new InventoryBasePage(getDriver());
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isElementPresent();
    }
}
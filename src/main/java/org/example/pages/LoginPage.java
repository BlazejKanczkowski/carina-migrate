package org.example.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.enums.UserCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage login(UserCredentials user) {
        return login(user.getUsername(), user.getPassword());
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isElementPresent();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public InventoryPage login(String username, String password) {
        usernameField.type(username);
        passwordField.type(password);
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isElementPresent();
    }
}

package org.example.services;

import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.example.pages.common.LoginBasePage;
import org.openqa.selenium.WebDriver;

public class LoginService implements ICustomTypePageFactory {
    private final WebDriver driver;

    public LoginService(WebDriver driver) {
        this.driver = driver;
    }

    public InventoryBasePage loginAs(UserCredentials user) {
        LoginBasePage loginPage = initPage(driver, LoginBasePage.class);
        loginPage.open();
        return loginPage.login(user);
    }
}

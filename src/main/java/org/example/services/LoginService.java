package org.example.services;

import org.example.enums.UserCredentials;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginService {
    private final WebDriver driver;

    public LoginService(WebDriver driver) {
        this.driver = driver;
    }

    public InventoryPage loginAs(UserCredentials user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        return loginPage.login(user);
    }
}

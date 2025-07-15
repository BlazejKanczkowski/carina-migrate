package org.example.services;

import org.example.enums.UserCredentials;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public final class SessionHelper {

    public static void openInventoryLoggedIn(WebDriver driver, UserCredentials user) {

        String base = "https://www.saucedemo.com";
        driver.get(base);

        String host = URI.create(driver.getCurrentUrl()).getHost();
        Cookie cookie = new Cookie.Builder("session-username", user.getUsername())
                .domain(host)
                .path("/")
                .build();
        driver.manage().addCookie(cookie);

        ((JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('session-username', arguments[0]);",
                        user.getUsername());

        driver.navigate().to(base + "/inventory.html");
    }
}

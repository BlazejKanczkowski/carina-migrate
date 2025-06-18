package org.example.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CartBasePage extends BasePage {

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    public CartBasePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutButtonPresent() {
        return checkoutButton.isElementPresent();
    }

    public CheckoutBasePage clickCheckout() {
        checkoutButton.waitUntil(ExpectedConditions.elementToBeClickable(checkoutButton.getElement()), Duration.ofSeconds(5));
        checkoutButton.click();
        return new CheckoutBasePage(getDriver());
    }
}
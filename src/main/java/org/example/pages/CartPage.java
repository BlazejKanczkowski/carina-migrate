package org.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutButtonPresent() {
        return checkoutButton.isElementPresent();
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage(getDriver());
    }
}
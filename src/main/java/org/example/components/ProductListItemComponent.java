package org.example.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductListItemComponent extends AbstractUIObject {

    @FindBy(className = "inventory_item_name")
    private ExtendedWebElement itemName;

    @FindBy(className = "inventory_item_price")
    private ExtendedWebElement price;

    @FindBy(css = "button.btn_inventory")
    private ExtendedWebElement addToCartButton;

    public ProductListItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public double getItemPrice() {
        String priceText = price.getText();
        if (priceText == null || priceText.isEmpty()) {
            throw new RuntimeException("Price text is null or empty for product item.");
        }
        try {
            return Double.parseDouble(priceText.replace("$", "").trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse price: " + priceText, e);
        }
    }

    public void clickAddToCart() {
        addToCartButton.waitUntil(ExpectedConditions.elementToBeClickable(addToCartButton.getElement()), Duration.ofSeconds(5));
        addToCartButton.click();
    }
}

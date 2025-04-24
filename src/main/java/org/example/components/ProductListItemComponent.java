package org.example.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

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
        return Double.parseDouble(price.getText().replace("$", "").trim());
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }
}

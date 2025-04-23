package org.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.components.ProductListItemComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.example.enums.SortOption;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends BasePage {

    @FindBy(xpath = "//span[contains(@data-test,'title')]")
    private ExtendedWebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;

    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cartIcon;

    @FindBy(className = "product_sort_container")
    private ExtendedWebElement sortDropdown;

    @FindBy(className = "inventory_item")
    private List<ProductListItemComponent> productItems;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortBy(SortOption option) {
        sortDropdown.select(option.getVisibleText());
    }

    public List<Double> getDisplayedPrices() {
        List<Double> prices = new ArrayList<>();
        for (ProductListItemComponent product : productItems) {
            String priceText = product.getItemPrice().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(getDriver());
    }

    public void addProductToCartByName(String productName) {
        for (ProductListItemComponent product : productItems) {
            if (product.getItemName().equalsIgnoreCase(productName)) {
                product.clickAddToCart();
                break;
            }
        }
    }

    public int getCartCount() {
        if (cartBadge.isDisplayed()) {
            return Integer.parseInt(cartBadge.getText());
        } else {
            return 0;
        }
    }

    public void waitForLogoutVisible() {
        logoutLink.waitUntil(ExpectedConditions.visibilityOf(logoutLink.getElement()), Duration.ofSeconds(3));
    }

    public void logout() {
        menuButton.click();
        waitForLogoutVisible();
        logoutLink.click();
    }

    public boolean isPageDisplayed() {
        return pageTitle.isDisplayed();
    }

    public void addProductsToCart(List<String> productNames) {
        for (String name : productNames) {
            addProductToCartByName(name);
        }
    }
}
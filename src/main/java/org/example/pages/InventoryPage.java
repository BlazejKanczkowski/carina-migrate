package org.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.example.components.ProductListItemComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.example.enums.SortOption;

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

    @FindBy(css = "[data-test='inventory-item']")
    private List<ProductListItemComponent> productItems;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortBy(SortOption option) {
        sortDropdown.select(option.getVisibleText());
    }

    public List<Double> getDisplayedPrices() {
        return productItems.stream()
                .map(ProductListItemComponent::getItemPrice)
                .collect(Collectors.toList());
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
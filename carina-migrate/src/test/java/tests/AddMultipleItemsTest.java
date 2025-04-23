package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddMultipleItemsTest extends BaseTest {

    @Test
    public void testAddMultipleProductsToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));

        Assert.assertEquals(inventoryPage.getCartCount(), 2, "Expected cart count to be 2");
    }
}
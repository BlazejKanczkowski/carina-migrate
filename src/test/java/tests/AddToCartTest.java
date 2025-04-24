package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack"));

        Assert.assertEquals(inventoryPage.getCartCount(), 1, "Expected cart count to be 1");
    }
}
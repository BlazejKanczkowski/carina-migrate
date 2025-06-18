package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.example.services.LoginService;

import java.util.List;

public class AddMultipleItemsTest extends BaseTest {

    @Test
    public void testAddMultipleProductsToCart() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));
        Assert.assertEquals(inventoryPage.getCartCount(), 2, "Expected cart count to be 2");
    }
}
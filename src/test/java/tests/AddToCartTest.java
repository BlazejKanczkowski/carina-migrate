package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddProductToCart() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack"));
        Assert.assertEquals(inventoryPage.getCartCount(), 1, "Expected cart count to be 1");
    }
}
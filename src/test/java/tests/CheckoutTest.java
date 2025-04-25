package tests;

import org.example.enums.UserCredentials;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void testProceedToCheckout() {

        InventoryPage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible");

        inventoryPage.addProductToCartByName("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.fillForm("John", "Doe", "12345");
        checkoutPage.finishOrder();

        Assert.assertTrue(checkoutPage.isConfirmationDisplayed(), "Order confirmation should be displayed");
    }
}
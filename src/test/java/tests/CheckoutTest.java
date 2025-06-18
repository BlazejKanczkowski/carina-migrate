package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.CartBasePage;
import org.example.pages.common.CheckoutBasePage;
import org.example.pages.common.InventoryBasePage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void testProceedToCheckout() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible");

        inventoryPage.addProductToCartByName("Sauce Labs Backpack");

        CartBasePage cartPage = inventoryPage.clickCartIcon();
        CheckoutBasePage checkoutPage = cartPage.clickCheckout();

        checkoutPage.fillForm("John", "Doe", "12345");
        checkoutPage.finishOrder();

        Assert.assertTrue(checkoutPage.isConfirmationDisplayed(), "Order confirmation should be displayed");
    }
}
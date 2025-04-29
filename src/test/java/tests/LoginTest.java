package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithStandardUser() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");
    }
}
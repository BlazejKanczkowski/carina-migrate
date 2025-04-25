package tests;

import org.example.enums.UserCredentials;
import org.example.pages.InventoryPage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithStandardUser() {
        InventoryPage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");
    }
}
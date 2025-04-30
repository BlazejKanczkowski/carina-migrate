package tests;

import org.example.enums.UserCredentials;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {

        InventoryPage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.logout();
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "User should be redirected to login page after logout");
    }
}
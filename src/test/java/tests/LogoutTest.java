package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.LoginBasePage;
import org.example.pages.common.InventoryBasePage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.logout();

        LoginBasePage loginPage = initPage(getDriver(), LoginBasePage.class);
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "User should be redirected to login page after logout");
    }
}
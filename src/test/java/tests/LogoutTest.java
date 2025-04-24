package tests;

import org.example.enums.UserCredentials;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login(UserCredentials.STANDARD_USER.getUsername(),
                UserCredentials.STANDARD_USER.getPassword()
        );
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.logout();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "User should be redirected to login page after logout");
    }
}
package tests;

import org.example.enums.UserCredentials;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithStandardUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login(UserCredentials.STANDARD_USER);

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");
    }
}
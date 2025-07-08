package tests;

import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.example.services.SessionHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CookieLoginHappyPathTest extends BaseTest {

    @Test
    public void userStartsOnInventoryWithoutUIlogin() {

        SessionHelper.openInventoryLoggedIn(getDriver(), UserCredentials.STANDARD_USER);

        InventoryBasePage inventory = new InventoryBasePage(getDriver());
        Assert.assertTrue(inventory.isPageOpened(), "Inventory page nie jest otwarta (user nie zalogowany).");
    }
}

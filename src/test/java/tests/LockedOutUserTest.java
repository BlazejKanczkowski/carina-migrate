package tests;

import org.example.enums.UserCredentials;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LockedOutUserTest extends BaseTest {

    @Test
    public void testLoginWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.login(UserCredentials.LOCKED_OUT_USER);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertTrue(
                loginPage.getErrorMessageText().toLowerCase().contains("locked out"),
                "Error message should mention user is locked out"
        );
    }
}
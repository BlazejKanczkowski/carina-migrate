package tests;

import org.example.enums.SortOption;
import org.example.enums.UserCredentials;
import org.example.pages.common.InventoryBasePage;
import org.example.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Collections;
import java.util.List;

public class SortProductsTest extends BaseTest {

    @Test
    public void testSortByPriceHighToLow() {

        InventoryBasePage inventoryPage = new LoginService(getDriver()).loginAs(UserCredentials.STANDARD_USER);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible");

        inventoryPage.sortBy(SortOption.PRICE_HIGH_TO_LOW);
        List<Double> actualPrices = inventoryPage.getDisplayedPrices();
        List<Double> sortedPrices = actualPrices.stream().sorted(Collections.reverseOrder()).toList();

        Assert.assertEquals(actualPrices, sortedPrices, "Products are not sorted from high to low by price");
    }
}
package tests;

import org.example.enums.SortOption;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Collections;
import java.util.List;

public class SortProductsTest extends BaseTest {

    @Test
    public void testSortByPriceHighToLow() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible");

        inventoryPage.sortBy(SortOption.PRICE_HIGH_TO_LOW);

        List<Double> actualPrices = inventoryPage.getDisplayedPrices();
        List<Double> sortedPrices = actualPrices.stream().sorted(Collections.reverseOrder()).toList();

        Assert.assertEquals(actualPrices, sortedPrices, "Products are not sorted from high to low by price");
    }
}
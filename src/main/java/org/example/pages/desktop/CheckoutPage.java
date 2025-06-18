package org.example.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.CheckoutBasePage;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CheckoutBasePage.class)
public class CheckoutPage extends CheckoutBasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
}
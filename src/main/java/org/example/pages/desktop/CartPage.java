package org.example.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.CartBasePage;
import org.openqa.selenium.WebDriver;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartBasePage.class)
public class CartPage extends CartBasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
}

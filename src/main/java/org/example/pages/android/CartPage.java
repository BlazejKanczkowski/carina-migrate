package org.example.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.CartBasePage;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartBasePage.class)
public class CartPage extends CartBasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
}

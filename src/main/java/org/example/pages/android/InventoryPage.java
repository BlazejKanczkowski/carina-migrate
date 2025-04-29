package org.example.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.InventoryBasePage;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = InventoryBasePage.class)
public class InventoryPage extends InventoryBasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
}
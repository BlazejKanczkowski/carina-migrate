package org.example.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.InventoryBasePage;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = InventoryBasePage.class)
public class InventoryPage extends InventoryBasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
}

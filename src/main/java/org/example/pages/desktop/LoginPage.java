package org.example.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.pages.common.LoginBasePage;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginBasePage.class)
public class LoginPage extends LoginBasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}

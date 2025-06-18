package tests;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.IDriverPool;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.example.utils.ConfigUtils;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest extends AbstractTest implements IDriverPool {

    @BeforeMethod
    public void setUp() {
        String driverType = System.getProperty("driver_type", ConfigUtils.getOrDefault("driver_type", "SELENIUM"));
        String seleniumUrl = ConfigUtils.getOrDefault("selenium_url", "http://localhost:4444/");
        System.setProperty("selenium_url", seleniumUrl);

        if ("APPIUM".equalsIgnoreCase(driverType)) {
            setUpAppiumDriver();
        } else {
            setUpSeleniumDriver();
        }

        String appUrl = ConfigUtils.getOrDefault("url", "https://www.saucedemo.com/");
        getDriver().get(appUrl);
    }

    private void setUpAppiumDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ConfigUtils.getOrDefault("capabilities.platformName", "Android"));
        capabilities.setCapability("platformVersion", ConfigUtils.getOrDefault("capabilities.platformVersion", "16.0"));
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("browserName", ConfigUtils.getOrDefault("capabilities.browserName", "chrome"));

        String chromedriverPath = ConfigUtils.getOrDefault("android.chromedriver.path", "");
        if (!chromedriverPath.isEmpty()) {
            capabilities.setCapability("chromedriverExecutable", chromedriverPath);
        }

        System.setProperty("device", "android_phone");
        setCapabilities(capabilities);
        getDriver();
    }

    private void setUpSeleniumDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-features=AutofillServerCommunication,PasswordManagerEnableAutosignin");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        String chromeArgs = ConfigUtils.getOrDefault("capabilities.chromeArgs", "");
        if (!chromeArgs.isEmpty()) {
            for (String arg : chromeArgs.split(",")) {
                options.addArguments(arg.trim());
            }
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        System.setProperty("device", "desktop");
        setCapabilities(capabilities);
        getDriver();
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}

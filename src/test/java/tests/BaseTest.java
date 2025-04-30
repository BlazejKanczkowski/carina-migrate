package tests;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.IDriverPool;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest extends AbstractTest implements IDriverPool {

    @BeforeMethod
    public void setUp() {
        String driverType = System.getProperty("driver_type", "SELENIUM");
        String seleniumUrl = System.getProperty("selenium_url", "http://localhost:4444/");

        System.setProperty("selenium_url", seleniumUrl);

        if ("APPIUM".equalsIgnoreCase(driverType)) {
            setUpAppiumDriver();
        } else {
            setUpSeleniumDriver();
        }

        getDriver().get("https://www.saucedemo.com/");
    }

    private void setUpAppiumDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("chromedriverExecutable",
                "/Users/blazejkanczkowski/Downloads/chromedriver128/chromedriver");
        System.setProperty("device", "android_phone");
        setCapabilities(capabilities);
        getDriver();
    }

    private void setUpSeleniumDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");

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

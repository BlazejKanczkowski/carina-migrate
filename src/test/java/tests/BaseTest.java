package tests;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.IDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest extends AbstractTest implements IDriverPool {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String driverType = System.getProperty("driver_type", "selenium");

        if ("APPIUM".equalsIgnoreCase(driverType)) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "16.0");
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("chromedriverExecutable",
                    "/Users/blazejkanczkowski/Downloads/chromedriver128/chromedriver");

            setCapabilities(capabilities);
            driver = getDriver();

        } else if ("SELENIUM".equalsIgnoreCase(driverType)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            setCapabilities(capabilities);
            driver = getDriver();
        }
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}

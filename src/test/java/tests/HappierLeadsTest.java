package tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.example.utils.ProxyServerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HappierLeadsTest extends BaseTest {
    @Test
    public void testHappierLeadsRequestIsCaptured() {
        WebDriver driver = getDriver(); 
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        ProxyServerUtil util = new ProxyServerUtil(driver, proxy);
        util.openWebsite("https://www.solvd.com");

        boolean found = util.isHappierLeadsRequestPresent();
        Assert.assertTrue(found, "HappierLeads request not found in HAR.");
    }
}

package org.example.utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProxyServerUtil {

    private final WebDriver driver;
    private final BrowserMobProxy proxy;

    public ProxyServerUtil(WebDriver driver, BrowserMobProxy proxy) {
        this.driver = driver;
        this.proxy = proxy;

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar("solvd");
    }

    public void openWebsite(String url) {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public boolean isHappierLeadsRequestPresent() {
        Har har = proxy.getHar();
        for (HarEntry entry : har.getLog().getEntries()) {
            String url = entry.getRequest().getUrl();
            if (url.contains("https://rest.happierleads.com")) {
                return true;
            }
        }
        return false;
    }
}

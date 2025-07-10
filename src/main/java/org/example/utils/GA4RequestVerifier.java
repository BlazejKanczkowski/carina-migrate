package org.example.utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.InetSocketAddress;
import java.time.Duration;

public class GA4RequestVerifier {

    private BrowserMobProxy proxy;
    private WebDriver driver;

    public void startProxyAndDriver() {
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // Forced IPv4
        InetSocketAddress proxyAddress = new InetSocketAddress("127.0.0.1", proxy.getPort());
        Proxy seleniumProxy = new Proxy();
        seleniumProxy.setHttpProxy(proxyAddress.getHostName() + ":" + proxyAddress.getPort());
        seleniumProxy.setSslProxy(proxyAddress.getHostName() + ":" + proxyAddress.getPort());

        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        options.addArguments("--ignore-certificate-errors");
        options.setAcceptInsecureCerts(true);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar("solvd");

        driver = new ChromeDriver(options);
    }

    public void openWebsite(String url) throws InterruptedException {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public boolean isGA4RequestPresent(String trackingId) {
        Har har = proxy.getHar();

        for (HarEntry entry : har.getLog().getEntries()) {
            String url = entry.getRequest().getUrl();
            if (url.contains("google-analytics.com/g/collect")
                    && url.contains("tid=" + trackingId)
                    && url.contains("en=page_view")) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        if (driver != null) driver.quit();
        if (proxy != null) proxy.stop();
    }
}

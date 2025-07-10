package tests;

import org.example.utils.GA4RequestVerifier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GA4AnalyticsTest {

    @Test
    public void testGA4TrackingRequestIsPresent() throws InterruptedException {
        GA4RequestVerifier verifier = new GA4RequestVerifier();
        try {
            verifier.startProxyAndDriver();
            verifier.openWebsite("https://www.solvd.com");

            boolean found = verifier.isGA4RequestPresent("G-CZYQYB58BC");
            Assert.assertTrue(found, "GA4 'page_view' request not found.");
        } finally {
            verifier.stop();
        }
    }
}

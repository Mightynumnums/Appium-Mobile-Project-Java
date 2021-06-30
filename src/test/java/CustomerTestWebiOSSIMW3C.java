import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomerTestWebiOSSIMW3C {

        private static final String sauceUser = System.getenv("SAUCE_USERNAME");
        private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
        String url = "https://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

        private IOSDriver driver;

        @Before
        public void setUp() throws MalformedURLException {
            DesiredCapabilities caps = new DesiredCapabilities();
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("appiumVersion","1.20.1");
            sauceOptions.setCapability("name", "Test customer");
            caps.setCapability("platformName","iOS");
            caps.setCapability("deviceName","iPhone 12 Simulator");
            caps.setCapability("platformVersion", "14.3");
            caps.setCapability("browserName", "Safari");
            caps.setCapability("deviceOrientation", "portrait");
            caps.setCapability("sauce:options", sauceOptions);

            driver = new IOSDriver(new URL(url), caps);
            try { Thread.sleep(3000); } catch (Exception ign) {}
        }

        @After
        public void cleanUpAfterTestMethod() {
            System.out.print(sauceUser);
            if (driver != null) {
                ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
                driver.quit();
            }
        }

        @Test
        public void test() {
            driver.get("https://google.com");
        }
}

import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestUsingSauceConnectW3CSim {
 //private static final String sauceUser = System.getenv("SAUCE_USERNAME");
  private static final String sauceUser = System.getenv("ALEKS.SHINELEVA");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    private static final String sauceTunnel = System.getenv("SAUCE_TUNNEL");
//    private static final String sauceUser = "alekstestsauce";
//    private static final String sauceKey = "b5a49da9-1673-4573-906b-8af3b70991d5";
//    private static final String sauceTunnel = "f5f6d0e4551e443992265560f301d4c6";

    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();

        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("deviceName", "iPhone 8 Simulator");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("appiumVersion", "1.17.1");
        sauceOptions.setCapability("tunnelIdentifier", sauceTunnel);
        sauceOptions.setCapability("name", "Sauce Tunnel Simple Test iOS");
        caps.setCapability("sauce:options", sauceOptions);
        System.out.println(caps);

        driver = new IOSDriver(new URL(url), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        driver.get("https://google.com");
//        driver.get("http://localhost:8003/HelloWorld.html");
//        driver.findElement(By.tagName("h1"));
    }
}

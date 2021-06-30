import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidEmuSimpleTestUsingAnAPP {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    private static final String sauceTunnel = System.getenv("SAUCE_TUNNEL");

    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com:80/wd/hub";
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {

        MutableCapabilities sauceOptions = new MutableCapabilities();
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("platformName","Android");
        caps.setCapability("deviceName","Android GoogleAPI Emulator");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("deviceOrientation", "portrait");
        sauceOptions.setCapability("appiumVersion", "1.17.1");
        //caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

        sauceOptions.setCapability("name","Android test for customer in W3C");
        caps.setCapability("sauce:options", sauceOptions);

        System.out.println(caps);

        driver = new AndroidDriver(new URL(url), caps);

    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.google.com");
        Thread.sleep(30000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
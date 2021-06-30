import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.lang.*;

import java.net.URL;

public class RDC_SC_Android {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    private static final String sauceTunnel = System.getenv("SAUCE_TUNNEL");

    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        //MutableCapabilities sauceOptions = new MutableCapabilities();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("name", "RDC Android Simple Test");
        capabilities.setCapability("appiumVersion", "1.18.1");
        capabilities.setCapability("deviceOrientation", "portrait");
        //capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("tunnelIdentifier", sauceTunnel);
       // sauceOptions.setCapability("tunnelIdentifier", sauceTunnel);
        //sauceOptions.setCapability("name", "Sauce Tunnel Simple Test ANDROID");
        //capabilities.setCapability("sauce:options", sauceOptions);
        System.out.println(capabilities);

        driver = new AndroidDriver(new URL(url), capabilities);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        //driver.get("http://google.com");
//        driver.get("http://localhost:8003/HelloWorld.html");
//        try {
//            driver.get("http://localwebapp:8003/HelloWorld.html");
//            Thread.sleep(2000);
//            driver.findElement(By.tagName("h1"));
//        } catch(InterruptedException e) {
//        }
    }
}

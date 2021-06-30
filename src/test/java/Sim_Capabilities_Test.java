import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Sim_Capabilities_Test {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    String APP = "storage:306f0f38-c2ec-4ab1-89e9-7cf4g1361c74";
    String url = "https://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";



    private IOSDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appiumVersion", "1.20.1");
        caps.setCapability("deviceName","iPhone 12 Simulator");
        caps.setCapability("platformVersion","14.3");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "");
        caps.setCapability("name", "Sauce Sample app Sim capabilities test");
        caps.setCapability("app", APP);

        driver =  new IOSDriver(new URL(url), caps);
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }
}


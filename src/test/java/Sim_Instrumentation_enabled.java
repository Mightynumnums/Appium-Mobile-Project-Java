import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Sim_Instrumentation_enabled {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    String APP = "storage:filename=app_name.zip";
    String url = "https://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";



    private IOSDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appiumVersion", "1.13.0");
        caps.setCapability("deviceName","iPhone X Simulator");
        caps.setCapability("platformVersion","12.4");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "");
        caps.setCapability("name", "Test Sim with no Instrumentation enabled iOS 12.4");
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

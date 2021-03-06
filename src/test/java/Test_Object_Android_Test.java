import io.appium.java_client.AppiumDriver;
import java.io.IOException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test_Object_Android_Test {
    public AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("testobject_api_key", "D98E6C58A01C4B3B954087B35E605C63");
        caps.setCapability("deviceName", "Samsung Galaxy S9");
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("name", "Android Test Object Java Test");
        this.driver = new AppiumDriver(new URL("https://us1.appium.testobject.com/wd/hub"), caps);
    }

    @Test
    public void testMethod() throws IOException, InterruptedException {
        this.driver.get("https://www.google.com");
    }

    @After
    public void cleanUpAfterTestMethod() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
            driver.quit();
        }
    }
}

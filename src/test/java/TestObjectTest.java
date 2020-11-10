import io.appium.java_client.AppiumDriver;
import java.io.IOException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestObjectTest {
    public AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("testobject_api_key", "D7A8AB865BEF475E88CB339D7E5B0024");
        caps.setCapability("deviceName", "Samsung Galaxy S9");
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("name", "Java Test Object Test");
        this.driver = new AppiumDriver(new URL("https://us1.appium.testobject.com/wd/hub"), caps);
    }

    @Test
    public void testMethod() throws IOException, InterruptedException {
        this.driver.get("https://www.google.com");
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }
}

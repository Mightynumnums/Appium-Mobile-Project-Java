import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;


public class RDC_Simple_Test {

    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    // This is the app stored in the SL storage
    String APP = "storage:9982ef38-4770-4bd7-88f4-a605d901e155";
    //String APP="storage: 7271be23-538a-4d80-b807-c0e902e387d2";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private AndroidDriver driver;


    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appiumVersion", "1.18.1");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "chrome");
        // You can set the platformVersion to a specific device OR tell it to just run whatever device is available for a platformVersion and platformName specified.
        //capabilities.setCapability("deviceName","Google_Pixel_3a_real");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("app", APP );
        driver =  new AndroidDriver(new URL(url), capabilities);
        try { Thread.sleep(3000); } catch (Exception ign) {}
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // If you want to have the simplest test EVER, just run the get method for a url (ex below) and nothing else.
//        driver.get("https://www.google.com/");
        WebElement userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-Username")));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(MobileBy.AccessibilityId("test-Password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginBtn = driver.findElement(MobileBy.AccessibilityId("test-LOGIN"));
        loginBtn.click();
        WebElement products = wait.until((ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-PRODUCTS"))));
    }
}


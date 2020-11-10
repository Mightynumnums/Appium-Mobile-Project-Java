import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class Mobile_Sauce_Test_Virtual_D {

    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    String APP = "storage:9982ef38-4770-4bd7-88f4-a605d901e155";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private AndroidDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appiumVersion", "1.18.1");
        capabilities.setCapability("deviceName","Samsung Galaxy S8 GoogleAPI Emulator");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity","com.swaglabsmobileapp.MainActivity");
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
        WebElement userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-Username")));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(MobileBy.AccessibilityId("test-Password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginBtn = driver.findElement(MobileBy.AccessibilityId("test-LOGIN"));
        loginBtn.click();
        WebElement products = wait.until((ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-PRODUCTS"))));
    }
}


import io.appium.java_client.MobileBy;
import java.net.URL;

import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Element_Basics_iOS_Local {
    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("deviceName", "iPhone 8");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", APP);
        driver = new IOSDriver(new URL(APPIUM), caps);
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

        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen")));
        screen.click();

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
        username.sendKeys("alice");

        WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
        password.sendKeys("mypassword");

        WebElement login = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
        login.click();

        WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));

        assert (loginText.getText().contains("alice"));
    }
}

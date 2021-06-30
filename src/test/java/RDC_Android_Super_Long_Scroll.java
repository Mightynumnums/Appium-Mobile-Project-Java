import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

//This test shows how to do super long scrolls using UiScrollable
public class MyBioAdvanced {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    //private static final String sauceTunnel = System.getenv("SAUCE_TUNNEL");
    String APP = "storage:filename=app-name.apk";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("name", "Android Long Scroll Test");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("deviceName", "Samsung_Galaxy_S21_Plus_5G_real_us");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformVersion","11");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("app", APP );
        System.out.println(capabilities);

        driver = new AndroidDriver(new URL(url), capabilities);
    }

    @Test
    public void test() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //log in
        WebElement userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Email, Required")));
        userNameInput.sendKeys("someValue");
        WebElement passwordInput = driver.findElement(MobileBy.AccessibilityId("Password, Required"));
        passwordInput.sendKeys("pass");

        WebElement loginBtn = driver.findElement(MobileBy.AccessibilityId("LOGIN"));
        new WebDriverWait(driver, 10);
        loginBtn.click();
        Thread.sleep(10000);

        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(45)" +
                        ".scrollIntoView(new UiSelector().text(\"©Inc. 2021 unless otherwise noted. All rights reserved\"))"));
        Thread.sleep(10000);
        WebElement agree = driver.findElement(MobileBy.AccessibilityId("Agree and Continue"));
        //Thread.sleep(10000);
        agree.isDisplayed();
        agree.click();

        //close the preview of features
        WebElement testMenu = driver.findElement(MobileBy.id("close-delete-card-modal"));
        Thread.sleep(10000);
        testMenu.click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


//trying Execute Script
//        Map<String, Object> args = new HashMap<>();
//        args.put("direction", "down");
//        args.put("strategy", "accessibility id");
//        args.put("selector", "Terms and Conditions");
//        driver.executeScript("mobile: scroll", args);

//Method that ALMOST WORKS
//        String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"© Janssen Inc. 2021 unless otherwise noted. All rights reserved\").instance(0))";
//        driver.findElementByAndroidUIAutomator(scrollElement).click();

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class iOSImageInjectionTest {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    //this is a storage id from my sauce storage
    String APP = "storage:2a892a46-849b-4b09-bfb1-acc9dd446d5e";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private IOSDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("name", "Image Injection Test");
        capabilities.setCapability("platformName","iOS");
        //capabilities.setCapability("platformVersion","14.3");
        capabilities.setCapability("timeZone","Denver");
        capabilities.setCapability("deviceName","iPhone 11*");
        capabilities.setCapability("appiumVersion", "1.18.1");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("sauceLabsImageInjectionEnabled", true);
        capabilities.setCapability("app", APP );
        driver =  new IOSDriver(new URL(url), capabilities);
        try { Thread.sleep(3000); } catch (Exception ign) {}
    }

    @Test
    public void test() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-Username")));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(MobileBy.AccessibilityId("test-Password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(MobileBy.AccessibilityId("test-LOGIN"));
        loginBtn.click();

        WebElement products = wait.until((ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("test-PRODUCTS"))));

        WebElement testMenu = driver.findElement(By.name("test-Menu"));
        testMenu.click();

        new WebDriverWait(driver, 8);
        WebElement  testMenuItemQRCode = driver.findElement(By.name("test-QR CODE SCANNER"));
        testMenuItemQRCode.click();

        new WebDriverWait(driver, 8);
        WebElement acceptCameraButton = driver.findElement(By.name("OK"));
        acceptCameraButton.click();
        new WebDriverWait(driver, 10);

        Utils utils = new Utils();
        // inject the image - provide the transformed image to the device with this command
        String qrCodeImage = utils.encoder("src/test/java/images/qr-code.png");
        ((JavascriptExecutor)driver).executeScript("sauce:inject-image=" + qrCodeImage);

        new WebDriverWait(driver, 8);
        Thread.sleep(10000);

    }

    @After
    public void cleanUpAfterTestMethod() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
            driver.quit();
        } else {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("failed"));
        }
    }

}

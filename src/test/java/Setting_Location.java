import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Setting_Location {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    String APP = "storage:e6f17fc9-01ab-484b-83f4-4fc6302fa179";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private IOSDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability("name", "Set Location test IOS");
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone 7 Simulator");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformVersion","12.2");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", APP );
        driver =  new IOSDriver(new URL(url), capabilities);

        try { Thread.sleep(3000); } catch (Exception ign) {}
    }

    @AfterEach
    public void cleanUpAfterTestMethod() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
            driver.quit();
        }
    }

    @Test
    public void LogIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-Username"))).sendKeys("standard_user");
        driver.findElement(By.id("test-Password")).sendKeys("secret_sauce");
        driver.findElement(By.id("test-LOGIN")).click();
      WebElement testMenuHamburger = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-Menu")));
        testMenuHamburger.click();
        WebElement geoLocationLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-GEO LOCATION")));
        geoLocationLink.click();
        driver.switchTo().alert().accept();
     WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Below you will find the latitude and longitude. You can use Appium to change them with this link.")));
  String textEl = text.getText();
//  assert(textEl.contains("Below you will find the latitude and longitude. You can use Appium to change them with this link."));
        WebElement latitude = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-latitude")));
        String latitudeResult = latitude.getText();
        assert(latitudeResult.contains("37."));
    }
}
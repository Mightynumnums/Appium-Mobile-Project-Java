import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mobile_Web_Captcha_IOS {

    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String SITE = "https://appiumpro.com";

    private RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("platformName", "iOS");
//        caps.setCapability("platformVersion", "12.2");
//        caps.setCapability("deviceName", "iPhone 8");
//        caps.setCapability("automationName", "XCUITest");
//        caps.setCapability("browserName", "Safari");

        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("deviceName", "[6-8]|X|11");
       // caps.setCapability("appiumVersion", "1.17.0");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("browserName", "Safari");
        driver = new RemoteWebDriver(new URL(APPIUM), caps);
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
        driver.get(SITE);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
        driver.findElement(By.linkText("Contact")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("smallHero___2kJmy")));
        driver.findElement(By.id("contactEmail")).sendKeys("youremail@gmail.com");
        driver.findElement(By.id("contactText")).sendKeys("Some very important text here.");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        driver.findElement(By.className("button___3QUY5")).click();
        String response = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error___2pSWM"))).getText();
        assert(response.contains("Your message could not be sent due to an error. The error message was: You must fill out the Captcha box"));
    }
}

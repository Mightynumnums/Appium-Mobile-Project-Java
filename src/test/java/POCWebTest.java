import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class POCWebTest {
    public IOSDriver driver;

    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    String URL = "https://" + sauceUser + ":" + sauceKey + "@ondemand.us-west-1.saucelabs.com/wd/hub";


    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "iPhone_XR_13_7_real_us");
        caps.setCapability("platformVersion", "13.7");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("name", "POC Test 13.7 and iphone XR inject JS");
        this.driver = new IOSDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testPOCLogin () {
        this.driver.get("https://sa.test.senseaware.com/web/wsawui/index.html#!login/index");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("" +
                "let user = document.querySelector('#user'); user.value='wsaw1880.s2@mailinator.com'; if (\"createEvent\" in document) {\n" +
                "    var evt = document.createEvent(\"HTMLEvents\");\n" +
                "    evt.initEvent(\"change\", false, true);\n" +
                "    user.dispatchEvent(evt);\n" +
                "}\n" +
                "else {\n" +
                "    user.fireEvent(\"onchange\");\n" +
                "}");

        js.executeScript("" +
                "let user = document.querySelector('#password'); user.value='Test1234'; if (\"createEvent\" in document) {\n" +
                "    var evt = document.createEvent(\"HTMLEvents\");\n" +
                "    evt.initEvent(\"change\", false, true);\n" +
                "    password.dispatchEvent(evt);\n" +
                "}\n" +
                "else {\n" +
                "    password.fireEvent(\"onchange\");\n" +
                "}");

        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
    }
    @After
    public void cleanUpAfterTestMethod() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
            driver.quit();
        }
    }
}




import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoubleTapEmuTest {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    //String APP = "storage:9982ef38-4770-4bd7-88f4-a605d901e155";
    String url = "http://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();
        capabilities.setCapability("appiumVersion", "");
        capabilities.setCapability("deviceName","Google Pixel 3 GoogleAPI Emulator");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformVersion","10.0");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("name", "Double Tap test setup");
        capabilities.setCapability("app", APP );
        driver = new AndroidDriver(new URL(url), capabilities);
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

        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("List Demo")));
        screen.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Altocumulus")));
        WebElement elem = driver.findElement(MobileBy.AccessibilityId("Altocumulus"));

        TouchActions actionOne = new TouchActions(driver);
        actionOne.doubleClick(elem);


        try { Thread.sleep(3000); } catch (Exception ign) {}



//        PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
//        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, Origin.viewport(), 520, 1530);
//        Interaction pressDown = finger.createPointerDown(MouseButton.LEFT.asArg());
//        Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), Origin.viewport(), 520, 490);
//        Interaction pressUp = finger.createPointerUp(MouseButton.LEFT.asArg());
//
//        Sequence swipe = new Sequence(finger, 0);
//        swipe.addAction(moveToStart);
//        swipe.addAction(pressDown);
//        swipe.addAction(moveToEnd);
//        swipe.addAction(pressUp);
//
//        driver.perform(Arrays.asList(swipe));
    }

}

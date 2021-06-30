import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class IOSSimW3CTest {
    private static final String sauceUser = System.getenv("SAUCE_USERNAME");
    private static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
    //app in US:
//    String APP = "storage:306f0f38-c2ec-4ab1-89e9-7cf4a1361c64";
    //app in EU:
    String APP = "storage:c1f068fa-080b-46d4-8971-11b6213ab999";
    //String url = "https://"+sauceUser+":"+sauceKey+"@ondemand.us-west-1.saucelabs.com/wd/hub";
    String url = "https://"+sauceUser+":"+sauceKey+"@ondemand.eu-central-1.saucelabs.com/wd/hub";

    private IOSDriver driver;

    @Before
    public void  setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("name","iOS App W3C test with sharable link");
        sauceOptions.setCapability("appiumVersion","1.20.1");
        caps.setCapability("platformName","iOS");
        caps.setCapability("appium:deviceName","iPhone 12 Simulator");
        caps.setCapability("appium:platformVersion", "14.3");
        caps.setCapability("browserName", "");
        caps.setCapability("appium:app", APP);
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("sauce:options", sauceOptions);

        driver =  new IOSDriver(new URL(url), caps);
        try { Thread.sleep(3000); } catch (Exception ign) {};
    }


    @After
    public void cleanUpAfterTestMethod() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        System.out.print(sauceUser);
         SauceHMACToken token = new SauceHMACToken();
         token.Token();


        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
            driver.quit();
        }
    }


    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

        public static class SauceHMACToken {

            public static void Token() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
                String key =  sauceUser+":"+sauceKey;
                //US
               // String message = "bd40fdad46bb4a60bdf541f545654dfb";
                //EU
                String message = "90d5f1aaa36b4fb1a5f631ddea2ea515";
                SecretKeySpec sks = new SecretKeySpec(key.getBytes("ASCII"),"HmacMD5");
                Mac mac = Mac.getInstance("HmacMD5");
                mac.init(sks);
                byte[] result = mac.doFinal(message.getBytes("ASCII"));
                StringBuffer hash = new StringBuffer();
                for (int i = 0; i < result.length; i++) {
                    String hex = Integer.toHexString(0xFF & result[i]);
                    if (hex.length() == 1) {
                        hash.append('0');
                    }
                    hash.append(hex);
                }
                String digest = hash.toString();
                //System.out.println("https://app.saucelabs.com/tests/"+message+"?auth=" + digest);
                System.out.println("https://app.eu-central-1.saucelabs.com/tests/"+message+"?auth=" + digest);
            }
            public static void main(String[] args) {
                try {
                    SauceHMACToken.Token();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
}

package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> driver;
    public static void start(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig config= GlobalConfig.load("/data/globalConfig.yaml");
        config.appium.capabilities.keySet().forEach(key->{
            Object value=config.appium.capabilities.get(key);
            desiredCapabilities.setCapability(key, value);
        });
/*

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("deviceName", "2a0c466c");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("resetKeyboard",true);
        desiredCapabilities.setCapability("unicodeKeyboard",true);
*/

        URL remoteUrl = null;
        try {
            remoteUrl = new URL(config.appium.url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(config.appium.wait, TimeUnit.SECONDS);
    }

    public static AndroidDriver<AndroidElement> getCurrentDriver(){
        return driver;
    }

}

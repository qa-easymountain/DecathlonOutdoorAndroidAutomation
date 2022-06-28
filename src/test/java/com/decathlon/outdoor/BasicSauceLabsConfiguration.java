package com.decathlon.outdoor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BasicSauceLabsConfiguration {
    public AppiumDriver driver;

    @BeforeEach
    public static AppiumDriver<AndroidElement> setup() throws MalformedURLException {
        System.out.println("Sauce Android App EMU Test - Before Each Hook");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("appium:deviceName","Android GoogleAPI Emulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion","12.0");
        caps.setCapability("appium:app", "storage:filename=app-release.apk");
        caps.setCapability("appPackage", "com.decathlon.quechuafinder");
        caps.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");

        URL url = new URL("https://oauth-pooja-55b27:5bcf8844-3320-4f17-8a9a-439d7758904c@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        AppiumDriver driver = new AndroidDriver(url, caps);
        return driver;
    }

    @AfterAll
    public void tearDown() {
        System.out.println("Sauce Android App EMU Test - After All TearDown");
        if(driver != null) {
            driver.quit();
        }
    }
}



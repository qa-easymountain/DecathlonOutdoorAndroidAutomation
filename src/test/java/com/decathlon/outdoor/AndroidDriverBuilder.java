package com.decathlon.outdoor;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverBuilder {

    public static AndroidDriver<AndroidElement> buildDriver(String apkFile) throws MalformedURLException {
        File app = new File("apk/" + apkFile);
        if (!app.exists()) {
            throw new IllegalStateException("the apk file was not found. Please add file " + apkFile + "in directory apk");
        }
        DesiredCapabilities cap = new DesiredCapabilities();
        //here we set our android emulator
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "PoojaEmulatorPixel5API32");
        //here we set our capability type
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        cap.setCapability("appPackage", "com.decathlon.quechuafinder");
        cap.setCapability("platform", "Android");
        cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
        //cap.setCapability("noReset", "true");
        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    private AndroidDriverBuilder() {
        // utility class
    }
}

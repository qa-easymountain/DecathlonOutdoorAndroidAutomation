package com.decathlon.outdoor;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverBuilder {
    public static AndroidDriver<AndroidElement> buildDriver() throws MalformedURLException {
        /* Below statement is used to print environment variable of the bitrise
        apk path which is set after the android build in bitrise */
        System.out.println(System.getenv("BITRISE_APK_PATH"));
        // Here we are setting the apk path for running the test locally
        String apkFile = ("Decathlonoutdoorandroid-2022051707.apk");
        //Here we provide apk file to the app variable
        File app = new File("apk/" + apkFile);
        // If the apk name is not present in the provided path show the exception message in terminal
        if (!app.exists() && System.getenv("BITRISE_APK_PATH") != null) {
            throw new IllegalStateException("the apk file was not found. Please add file " + apkFile + " in directory apk");
        }
        //Instantiating the Desired capabilities inside the cap variable
        DesiredCapabilities cap = new DesiredCapabilities();
        //here we set our android emulator
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "PoojaEmulatorAppDebug");
        //here we set our capability type
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("platform", "Android");

        if(System.getenv("BITRISE_APK_PATH") == null) {
            cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            cap.setCapability("appPackage", "com.decathlon.quechuafinder");
            cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
        } else {
            cap.setCapability(MobileCapabilityType.APP, System.getenv("BITRISE_APK_PATH"));
            cap.setCapability("appPackage", "com.decathlon.quechuafinder.alpha");
            cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
            //cap.setCapability("appActivity", "com.easymountain.quechua.ui.splash.SplashActivity");
        }

        return new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
    }

    private AndroidDriverBuilder() {
        // utility class
    }
}

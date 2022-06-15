package com.decathlon.outdoor;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class AndroidDriverBuilder {
    public static AndroidDriver<AndroidElement> buildDriver() throws MalformedURLException {
        /* Below statement is used to print environment variable of the bitrise
        apk path which is set after the android build in bitrise */
        System.out.println(System.getenv("BITRISE_APK_PATH"));
        // Here we are setting the apk path for running the test locally
        String apkFileName = "release-2022060714.apk";
        String apkPath = "apk/" + apkFileName;
        if (Objects.equals(System.getenv("BITRISE_APK_PATH"), "")) {
            apkPath = System.getenv("BITRISE_APK_PATH");
        }

        //Here we provide apk file to the app variable
        File app = new File(apkPath);
        // If the apk name is not present in the provided path show the exception message in terminal
        if (!app.exists() && System.getenv("BITRISE_APK_PATH") != null) {
            throw new IllegalStateException("the apk file was not found. Please add file " + apkPath + " in directory apk");
        }
        //Instantiating the Desired capabilities inside the cap variable
        DesiredCapabilities cap = new DesiredCapabilities();
        //here we set our android emulator
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "PoojaEmulatorAppDebug");
        //here we set our capability type
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("platform", "Android");


        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        if(apkFileName == "app-alpha.apk") {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder.alpha");
        } else if(apkFileName == "app-debug.apk") {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder.debug");
        } else {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder");
        }
        cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
        return new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
    }

    private AndroidDriverBuilder() {
        // utility class
    }
}

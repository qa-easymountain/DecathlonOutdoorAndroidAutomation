package com.decathlon.outdoor;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class ExampleTest {

    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        File app = new File("apk/4.16.0 adding_missing_ids.apk");
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
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @Test
    @DisplayName("it should do display the expected text")
    void test1() {
        // Given
        // 'theElement' is clickable
        var theElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("the-element-id")));

        // When
        // 'theElement' is clicked
        theElement.click();

        // Then
        // 'theOtherElement' should be visible
        var theOtherElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("the-other-element-id")));

        // 'theOtherElement''s text should be as expected
        Assertions.assertThat(theOtherElement.getText()).isEqualTo("my awesome text");
    }
}

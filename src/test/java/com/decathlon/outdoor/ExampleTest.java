package com.decathlon.outdoor;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

class ExampleTest {

    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        var apkFile = "Decathlonoutdoorandroid-2022051707.apk";
        driver = AndroidDriverBuilder.buildDriver(apkFile);
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

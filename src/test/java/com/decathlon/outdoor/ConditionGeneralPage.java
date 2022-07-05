package com.decathlon.outdoor;

    import io.appium.java_client.AppiumDriver;
    import io.appium.java_client.MobileBy;
    import io.appium.java_client.android.AndroidElement;
    import org.assertj.core.api.Assertions;
    import org.junit.jupiter.api.*;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import java.io.IOException;
    import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConditionGeneralPage {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws IOException {
        //   driver = BasicSauceLabsConfiguration.setup();
        driver = BrowserstackBuilder.buildDriver("ConditionGeneralePage");
        //driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should be able to open Condition Generale Page")
    public void userLogin() throws InterruptedException {
        String packageName = driver.getCapabilities().getCapability("appPackage").toString();

        AndroidElement acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");

        //Onboard and procees Button
        AndroidElement welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName +":id/onboard_proceed_btn")));
        welcomeAgree.click();

        Object OsVersion  = driver.getCapabilities().getCapability("os_version");

        if(Objects.equals(OsVersion.toString(), "12.0")) {
            // select location next to you
            AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver, 30).until(
                    //ExpectedConditions.elementToBeClickable(MobileBy.id("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")));
                    ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.RadioButton[@resource-id='com.android.permissioncontroller:id/permission_location_accuracy_radio_fine']")));

            locationSelector.click();

            // give permission while using application
            AndroidElement locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                    //ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
            );
            locationPermissionAllowUsingThisAppButton.click();

        } else if (Objects.equals(OsVersion.toString(), "11.0")) {
            AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
            locationSelector.click();
        }

        // GoTo Profile Page for Login (click on profile option)
        Thread.sleep(2000);
        AndroidElement profilePageButton = (AndroidElement) new WebDriverWait(driver, 100).until(
                //ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/profile_nav_menu"))
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.FrameLayout[@content-desc='Profil']"))
        );
        profilePageButton.click();
        
        //Thread.sleep(3000);

        try {
            AndroidElement batchEventCloseButton = (AndroidElement) new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/com_batchsdk_messaging_close_button"))
            );
            System.out.println("Batch special event button is present");
            batchEventCloseButton.click();
        } catch (Exception e ) {
            System.out.println("Batch special event button was not present");
        }
        
        AndroidElement settingsButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/settings"))
        );
        settingsButton.click();

        AndroidElement tapCGUButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/general_conditions"))
        );
        tapCGUButton.click();
        Thread.sleep(3000);

        AndroidElement checkText = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Image[@text='Decathlon outdoor - Decathlon']"))
        );

        if(checkText.isDisplayed()) {
            System.out.println("Condition Generales D'utilisation Page Opens Successfully");
        } else {
            System.out.println("Test failed");
        }

        Thread.sleep(5000);

    }


    @AfterEach
    public void tearDown() {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }
}


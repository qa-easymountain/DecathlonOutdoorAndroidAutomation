package com.decathlon.outdoor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
     //   driver = BasicSauceLabsConfiguration.setup();
        driver = AndroidDriverBuilder.buildDriver("Login");


    }

    @Test
    @DisplayName("it should be able to login")
    public void userLogin() throws InterruptedException {
        String packageName = driver.getCapabilities().getCapability("appPackage").toString();
        //String packageName = "com.decathlon.quechuafinder";
        //System.out.println(packageName);

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
        //Thread.sleep(4000);
        AndroidElement profilePageButton = (AndroidElement) new WebDriverWait(driver, 100).until(
                //ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/profile_nav_menu"))
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.FrameLayout[@content-desc='Profil']"))
        );
        profilePageButton.click();

        //click on connect to login page //Continue avec decathlon
        //Thread.sleep(3000);
        AndroidElement decathlonConnectionButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.ViewGroup[@resource-id='" + packageName + ":id/btn_decathlon_login']"))
        );
        decathlonConnectionButton.click();

        //Insert emailId

        //Thread.sleep(30000);
        AndroidElement userNameInput = (AndroidElement) new WebDriverWait(driver, 50).until(
                //Formula of xpath=> .xpath("//Class name[@attribute name='value']")
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='input-email']"))
        );
        userNameInput.click();
        userNameInput.sendKeys("test.decathlonoutdoor@gmail.com");

          driver.hideKeyboard();
        //click on next button
        //Thread.sleep(3000);
        AndroidElement nextButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='lookup-btn']"))
        );
        nextButton.click();

        // insert password
        //Thread.sleep(3000);
        AndroidElement passwordInput = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='input-password']"))
        );
        passwordInput.sendKeys("4SG!!7xG");
        driver.hideKeyboard();

        //click on sign in button valider
        //Thread.sleep(3000);
        AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='signin-button']"))
        );

        signinButton.click();



        //Check whether you are logged-in with username
        Thread.sleep(3000);

        try {
            AndroidElement batchEventCloseButton = (AndroidElement) new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/com_batchsdk_messaging_close_button"))
            );
            System.out.println("Batch special event button is present");
            batchEventCloseButton.click();
        } catch (Exception e ) {
            System.out.println("Batch special event button was not present");
        }

        AndroidElement usernameProfile = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/username_profile"))
        );
        Assertions.assertThat(usernameProfile.isDisplayed()).isEqualTo(true);
        System.out.println("Login Successfully");

        Thread.sleep(5000);


        AndroidElement settingsButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/settings"))
        );
        settingsButton.click();

        Thread.sleep(3000);
        AndroidElement logoutButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/disconnect_btn"))
        );
        logoutButton.click();
        System.out.println("Logout after Successful login");

        Thread.sleep(5000);

    }

    @AfterEach
    public void tearDown() {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }

}

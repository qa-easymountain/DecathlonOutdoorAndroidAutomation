package com.decathlon.outdoor;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class Login {
    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        var apkFile = ("Decathlonoutdoorandroid-2022051707.apk");
        driver = AndroidDriverBuilder.buildDriver(apkFile);
    }

    @Test
    @DisplayName("it should be able to login")
    void login() throws InterruptedException{

        var acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.decathlon.quechuafinder:id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");

        //Onboard and procees Button
        var welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.decathlon.quechuafinder:id/onboard_proceed_btn")));
        welcomeAgree.click();

        // select location next to you
        var locationSelector = (AndroidElement) new WebDriverWait(driver,  30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")));
        locationSelector.click();

        // give permission while using application
        var locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        locationPermissionAllowUsingThisAppButton.click();

        // GoTo Profile Page for Login (click on profile option)
        //Thread.sleep(3000);
        var profilePageButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/profile_nav_menu"))
        );
        profilePageButton.click();

        //click on connect to login page //Continue avec decathlon
        //Thread.sleep(3000);
        var decathlonConnectionButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/btn_decathlon_login"))
        );
        decathlonConnectionButton.click();

        //Insert emailId

        //Thread.sleep(30000);
        var userNameInput = (AndroidElement) new WebDriverWait(driver, 50).until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.EditText"))
        );
        userNameInput.click();
        userNameInput.sendKeys("test.decathlonoutdoor@gmail.com");


        //click on next button
        //Thread.sleep(3000);
        var nextButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button"))
        );
        nextButton.click();

        // insert password
        //Thread.sleep(3000);
        var passwordInput = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View[3]/android.widget.EditText"))
        );

        passwordInput.sendKeys("4SG!!7xG");

        //click on sign in button valider
        //Thread.sleep(3000);
        var signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button"))
        );

        signinButton.click();

        //Check whether you are logged-in with username
        //Thread.sleep(1000);
        //@SuppressWarnings("unused")
        var usernameProfile = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/username_profile"))
        );

        usernameProfile.isDisplayed();
        System.out.println("Login Successfully");

        //Thread.sleep(3000);

        driver.quit();
        
    }
}

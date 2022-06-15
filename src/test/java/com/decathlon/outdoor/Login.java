package com.decathlon.outdoor;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {
    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should be able to login")
    public void login() throws InterruptedException {

        String packageName = driver.getCurrentPackage();
        
        AndroidElement acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");
        Thread.sleep(3000);
        try {
            AndroidElement newVersionAfterButton = driver.findElement(MobileBy.xpath("//android.widget.Button[@text='PLUS TARD']"));
            newVersionAfterButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("new version message dialog box failed to display");
        }

        //Onboard and procees Button
        AndroidElement welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName +":id/onboard_proceed_btn")));
        welcomeAgree.click();

        // select location next to you
        AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver,  30).until(
                //ExpectedConditions.elementToBeClickable(MobileBy.id("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")));
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.RadioButton[@resource-id='com.android.permissioncontroller:id/permission_location_accuracy_radio_fine']")));

        locationSelector.click();

        // give permission while using application
        AndroidElement locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        locationPermissionAllowUsingThisAppButton.click();

        // GoTo Profile Page for Login (click on profile option)
        Thread.sleep(2000);
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

        //click on sign in button valider
        //Thread.sleep(3000);
        AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='signin-button']"))
        );


        signinButton.click();

        //Check whether you are logged-in with username
        Thread.sleep(3000);
        AndroidElement usernameProfile = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/username_profile"))
        );

        Assertions.assertThat(usernameProfile.isDisplayed()).isEqualTo(true);

        System.out.println("Login Successfully");
        
    }

    @AfterAll()
    public void tearDown() {
        if(null != driver) {
            driver.quit();
        }
    }
}

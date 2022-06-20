package com.decathlon.outdoor;

        import  io.appium.java_client.MobileBy;
        import io.appium.java_client.PerformsTouchActions;
        import io.appium.java_client.TouchAction;
        import io.appium.java_client.android.AndroidDriver;
        import io.appium.java_client.android.AndroidElement;
        import io.appium.java_client.touch.WaitOptions;
        import io.appium.java_client.touch.offset.PointOption;
        import org.assertj.core.api.Assertions;
        import org.junit.jupiter.api.*;
        import org.openqa.selenium.By;
        import org.openqa.selenium.Dimension;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.net.MalformedURLException;
        import java.time.Duration;
        import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class createnewuser {
    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should download the random hike")
    public void createnewuser() throws InterruptedException {
        String packageName = driver.getCurrentPackage();

        AndroidElement acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");

        //Onboard and procees Button
        AndroidElement welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/onboard_proceed_btn")));
        welcomeAgree.click();

        // select location next to you
        AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver,  30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")));
        locationSelector.click();

        // give permission while using application
        AndroidElement locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        locationPermissionAllowUsingThisAppButton.click();

        Thread.sleep(3000);
        Faker fake = new Faker();
        String emailAddress  = fake.internet().emailAddress();
        AndroidElement userNameInput = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.className("android.widget.EditText"))
        );
        userNameInput.click();
        userNameInput.sendKeys(emailAddress);
        //System.out.println(emailAddress);


        //click on next button
        Thread.sleep(3000);
        AndroidElement signupButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.className("android.widget.Button"))
        );

        signupButton.click();

        // insert password
        Thread.sleep(3000);
        AndroidElement passwordInput = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.className("android.widget.EditText"))
        );
        passwordInput.click();
        passwordInput.sendKeys("4SG!!7xG");

        //click on sign in button valider android.widget.Button
        Thread.sleep(3000);
        AndroidElement registerButton =  (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[3]/android.widget.Button"))
        );

        registerButton.click();

        Thread.sleep(3000);
        AndroidElement userName = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/sign_up_firstname_input_txt"))
        );
        String fakeName = fake.name().firstName();
        userName.sendKeys(fakeName);


        AndroidElement signupNextButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/sign_up_next_btn"))
        );
        signupNextButton.click();

        //Recieve News Letter
        //AndroidElement recieveNewsLetterButton = (AndroidElement) new WebDriverWait(driver, 30).until(
        //      ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/recieve_newletter_btn"))
        //);
        //recieveNewsLetterButton.click();

        //Skip News Letter
        AndroidElement skipNewsLetterButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/skip_newletter_btn"))
        );
        skipNewsLetterButton.click();


        Thread.sleep(1000);
        AndroidElement usernameprofile = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/username_profile"))
        );

        usernameprofile.getText().matches(fakeName);


        System.out.println("Registered Successfully");
    }



    @AfterAll()
    public void tearDown() {
        if(null != driver) {
            driver.quit();
        }
    }
}




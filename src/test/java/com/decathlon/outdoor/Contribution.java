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
public class Contribution {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws IOException {
        //   driver = BasicSauceLabsConfiguration.setup();
        driver = BrowserstackBuilder.buildDriver("Contribution");
        //driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should be able to click on go button and return back")
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

        // Tap on Créer button
        Thread.sleep(4000);
        AndroidElement createhikeButton = (AndroidElement) new WebDriverWait(driver, 100).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/open_sdk_editor"))
        );
        createhikeButton.click();

        //click on connect to login page //Continue avec decathlon
        Thread.sleep(2000);
        AndroidElement decathlonConnectionButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                //ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.ViewGroup[@resource-id='" + packageName + ":id/btn_decathlon_login']"))
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/btn_decathlon_login"))
        );
        decathlonConnectionButton.click();

        //Insert emailId
        Thread.sleep(1000);
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
        Thread.sleep(1000);
        AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='signin-button']"))
        );

        signinButton.click();
        System.out.println("Login Successfully");

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

        //Click on Creer une sortie button
        Thread.sleep(1000);
        AndroidElement createSortieButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/btn_create_new_hike_container_empty_list_view"))
        );
        createSortieButton.click();
        System.out.println("Create hike button is working properly");

        //Click on camera button
        Thread.sleep(1000);
        AndroidElement cameraButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/take_picture_view"))
        );
        cameraButton.click();


        //Click on allow photos button
        Thread.sleep(1000);
        AndroidElement allowPhotosButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        allowPhotosButton.click();

        //Click on shutter button
        Thread.sleep(1000);
        AndroidElement shutterButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.camera2:id/shutter_button"))
                //ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.ImageView[@resource-id='com.android.camera2:id/shutter_button']")));

        );
        shutterButton.click();

        //Click on save photo button
        Thread.sleep(1000);
        AndroidElement savePhotoButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.camera2:id/done_button"))
                //ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.ImageButton[@resource-id='com.android.camera2:id/done_button']")));

        );
        savePhotoButton.click();
        System.out.println("Camera button is working fine");

        //Click on Go button
        Thread.sleep(2000);
        AndroidElement goButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/startRecordContainer"))
        );
        goButton.click();
        System.out.println("Go to hike button is working properly");

        //Click on Pause Button
        Thread.sleep(3000);
        AndroidElement pauseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/pause_button_container"))
        );
        pauseButton.click();
        System.out.println("Pause button is working properly");

        //Click on Close (cross) Button
        Thread.sleep(3000);
        AndroidElement closeButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/stop_button_container"))
        );
        closeButton.click();
        System.out.println("Cross button is working properly");

        //Click on "resume Button" reprendre button
        Thread.sleep(3000);
        AndroidElement resumeButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/button_resume_recording"))
        );
        resumeButton.click();
        System.out.println("Reprendre button is working properly");

        //Click on Close (cross) Button
        Thread.sleep(3000);
        AndroidElement crossButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/stop_button_container"))
        );
        crossButton.click();
        System.out.println("Cross button is working properly");

        //Click on supprimer et quitter
        Thread.sleep(3000);
        AndroidElement quitButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/button_finish_recording_for_limit_distance"))
        );
        quitButton.click();
        System.out.println("Quit button is working properly");


        //Check whether you are on creer page again
        Thread.sleep(1000);
        AndroidElement myCreationPage = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/tv_title_empty_creation_hikes"))
        );
        Assertions.assertThat(myCreationPage.isDisplayed()).isEqualTo(true);
        System.out.println("My creation tabs working properly");

    }

    @AfterEach
    public void tearDown() {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }

}


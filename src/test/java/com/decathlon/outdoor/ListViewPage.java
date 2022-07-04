package com.decathlon.outdoor;

import io.appium.java_client.AppiumDriver;
import  io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListViewPage {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws IOException {
        //driver = AndroidDriverBuilder.buildDriver();
        driver = BrowserstackBuilder.buildDriver("RandomHikeDownload");
    }

    @Test
    @DisplayName("It should tap on List View Page and download the random hike")
    public void taponlistviewpage() throws InterruptedException {
        String packageName = driver.getCapabilities().getCapability("appPackage").toString();

        AndroidElement acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");

        //Onboard and procees Button
        AndroidElement welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/onboard_proceed_btn")));
        welcomeAgree.click();

        Object OsVersion = driver.getCapabilities().getCapability("os_version");

        if (Objects.equals(OsVersion.toString(), "12.0")) {

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

        // Tap on list view
        Thread.sleep(3000);
        AndroidElement listView = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/container_map_list_button"))
        );
        listView.click();

        //Random scroll
        Thread.sleep(3000);
        Random dice = new Random();

        int number;

        number = 1 + dice.nextInt(6);

        System.out.println(number);

        //int count = number;

        for (int count = 1; count <= number; count++) {
            swipePanel(driver);
            //System.out.print(count);
        }

        Thread.sleep(3000);
        AndroidElement routName = driver.findElementByXPath("//android.widget.FrameLayout[@index='1']");
        routName.click();

        //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        Thread.sleep(3000);
        AndroidElement downloadRouteButton = driver.findElementById(packageName + ":id/mp_detail_btn_download");
        downloadRouteButton.click();

        Thread.sleep(3000);
        AndroidElement continueWithDecathlonIdButton = driver.findElementById(packageName + ":id/btn_decathlon_login");
        continueWithDecathlonIdButton.click();

        Thread.sleep(2000);
        ExpectedConditions.titleContains("Connexion");

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

        //click on sign in button
        Thread.sleep(1000);
        AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='signin-button']"))
        );

        signinButton.click();

        Thread.sleep(3000);

        try {
            AndroidElement batchEventCloseButton = (AndroidElement) new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/com_batchsdk_messaging_close_button"))
            );
            System.out.println("Batch special event button is present");
            batchEventCloseButton.click();
        } catch (Exception e) {
            System.out.println("Batch special event button was not present");
        }

        //Demaree
        //Thread.sleep(1000);
        AndroidElement startButton = (AndroidElement) new WebDriverWait(driver, 300).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"))
        );

        startButton.click();

        //Thread.sleep(7000);
        System.out.println("Random hike downloaded Successfully");

    }

    public static void swipePanel(AppiumDriver<AndroidElement> driver) {

        TouchAction action =new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int width = size.width;
        int height = size.height;
        int middleOfX = width/2;
        int startYCoordinate = (int)(height*.75);
        int endYCoordinate = (int)(height*.2);

        action.press(PointOption.point(middleOfX, startYCoordinate))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(middleOfX, endYCoordinate))
                .release().perform();

    }


    @AfterAll()
    public void tearDown() {
       // if (null != driver) {
            driver.quit();
        }
    }
//}







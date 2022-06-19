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
public class randomhikedownload {
    private AndroidDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should download the random hike")
    public void randomhikedownload() throws InterruptedException {
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
                //===================== Random Location Selection =====================
                String[] locations = {"Grenoble", "Lyon", "Paris", "Toulouse", "Nice", "Bordeaux", "Valence", "Annecy"};
                Random r = new Random();
                int randomNumber= r.nextInt(locations.length);
                String randomLocation = locations[randomNumber];
    
                Thread.sleep(3000);
                //Click on search bar
                AndroidElement locationSearch = driver.findElementById(packageName + ":id/search_with_text_click_area");
                locationSearch.click();
                Thread.sleep(3000);
    
                //type the random location
                AndroidElement locationSearchArea = driver.findElementById(packageName + ":id/edit_text_search_input");
                locationSearchArea.sendKeys(randomLocation);
                Thread.sleep(3000);
    
    
                AndroidElement locationSelection = driver.findElementById(packageName + ":id/place_title");
                locationSelection.click();
    
                //===================== Swipe for beta test ===================
                Thread.sleep(3000);
                //WebElement panel = driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout[2]"));
                WebElement panel = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='com.decathlon.quechuafinder:id/white_container_info_hike']"));

                Random dice = new Random();
    
                int number;
    
                number = 1 + dice.nextInt(6);
    
                System.out.print(number);
    
                //int count = number;
    
                for(int count=1; count <= number; count++) {
                    swipePanel(panel, driver);
                    System.out.print(count);
                }
    
    
                //===================== End: Swipe for beta test ===================
    
    
                //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
                Thread.sleep(3000);
                AndroidElement routName = driver.findElementById(packageName + ":id/tv_hike_item_title");
                routName.click();
    
                //ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/tv_hike_item_title"));
    
                // AndroidElement ackknowledgeButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[2]");
                //ackknowledgeButton.click();
    
                //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
                Thread.sleep(3000);
                AndroidElement downloadRouteButton = driver.findElementById(packageName + ":id/mp_detail_btn_download");
                downloadRouteButton.click();
    
                Thread.sleep(3000);
                AndroidElement continueWithDecathlonIdButton = driver.findElementById(packageName + ":id/btn_decathlon_login");
            /*AndroidElement continueWithDecathlonIdButton = (AndroidElement) new WebDriverWait(driver, 3000).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/btn_decathlon_login"))
            );*/
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
            //userNameInput.sendKeys("pooja@easy-mountain.com");


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
            //passwordInput.sendKeys("4SG!!7xG");
            //passwordInput.sendKeys("Pooja123");

            //click on sign in button
            Thread.sleep(1000);
            AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='signin-button']"))
            );

            signinButton.click();

            //Thread.sleep(1000);
            AndroidElement startButton = (AndroidElement) new WebDriverWait(driver, 300).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"))
            );

            startButton.click();

            //Thread.sleep(7000);
            System.out.println("Random hike downloaded Successfully");
        }

    public static void swipePanel(WebElement el, AndroidDriver<AndroidElement> driver) {

        WebElement panel = el;

        Dimension dimension = panel.getSize();

        Double panelScreenHeight = dimension.getHeight() / 1.2;
        int anchor = panelScreenHeight.intValue();

        Double panelScreenWidthStart = dimension.getWidth() * 0.8;
        int swipeStart = panelScreenWidthStart.intValue();


        Double panelScreenWidthEnd = dimension.getWidth() * 0.1;
        int swipeEnd = panelScreenWidthEnd.intValue();

        new TouchAction<>((PerformsTouchActions) driver)
                .press(PointOption.point(swipeStart,anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(swipeEnd,anchor))
                .release().perform();
    }

    @AfterAll()
    public void tearDown() {
        if(null != driver) {
            driver.quit();
        }
    }
}





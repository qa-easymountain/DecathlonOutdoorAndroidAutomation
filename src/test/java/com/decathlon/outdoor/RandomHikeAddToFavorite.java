package com.decathlon.outdoor;

    import io.appium.java_client.AppiumDriver;
    import io.appium.java_client.MobileBy;
    import io.appium.java_client.PerformsTouchActions;
    import io.appium.java_client.TouchAction;
    import io.appium.java_client.android.AndroidElement;
    import io.appium.java_client.touch.WaitOptions;
    import io.appium.java_client.touch.offset.PointOption;
    import org.junit.jupiter.api.*;
    import org.openqa.selenium.By;
    import org.openqa.selenium.Dimension;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.io.IOException;
    import java.time.Duration;
    import java.util.Objects;
    import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomHikeAddToFavorite {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws IOException {
        //driver = AndroidDriverBuilder.buildDriver();
        driver = BrowserstackBuilder.buildDriver("RandomHikeAddToFavorite");
    }

    @Test
    @DisplayName("it should add the random hike to Favorite")
    public void addtoFavoriteRandomHike() throws InterruptedException {
        String packageName = driver.getCapabilities().getCapability("appPackage").toString();
        System.out.println(packageName);

        AndroidElement acceptAndCloseButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/button_agree")));
        acceptAndCloseButton.click();
        System.out.println("Cliqué sur Accepter & Fermer");

        //Onboard and procees Button
        AndroidElement welcomeAgree = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id(packageName + ":id/onboard_proceed_btn")));
        welcomeAgree.click();
        
        Object OsVersion = driver.getCapabilities().getCapability("os_version");

        if(Objects.equals(OsVersion.toString(), "12.0")) {

        // select location next to you
        AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver,  30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine")));
        locationSelector.click();

        // give permission while using application
        AndroidElement locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        locationPermissionAllowUsingThisAppButton.click();


        } else if (Objects.equals(OsVersion.toString(), "11.0")) {
            AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
            locationSelector.click();
        }

        Thread.sleep(3000);
        //===================== Random Location Selection =====================
        String[] locations = {"Montpellier", "Lyon", "Paris", "Toulouse", "Nice", "Bordeaux", "Chamonix", "Annecy"};
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


        Thread.sleep(3000);
        WebElement panel = driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout[2]"));

        Random dice = new Random();

        int number;

        number = 1 + dice.nextInt(6);

        System.out.print(number);

        //int count = number;

        for(int count=1; count <= number; count++) {
            swipePanel(panel, driver);
            //System.out.print(count);
        }

        //===================== End: Swipe for beta test ===================

        Thread.sleep(3000);
        AndroidElement addFavorite = driver.findElementById(packageName + ":id/add_favorite");
        addFavorite.click();

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

        try {
            AndroidElement batchEventCloseButton = (AndroidElement) new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/com_batchsdk_messaging_close_button"))
            );
            System.out.println("Batch special event button is present");
            batchEventCloseButton.click();
        } catch (Exception e ) {
            System.out.println("Batch special event button was not present");
        }

        // Add to favorite // See heart
        Thread.sleep(1000);
        AndroidElement FavorisButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.ImageView[@resource-id='com.decathlon.quechuafinder:id/add_favorite']"))
        );

        FavorisButton.click();

        //Thread.sleep(7000);
        System.out.println("Random hike added to my favorite Successfully");
    }

    public static void swipePanel(WebElement el, AppiumDriver<AndroidElement> driver) {

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
        //if(null != driver) {
            driver.quit();
        }
    }
//}




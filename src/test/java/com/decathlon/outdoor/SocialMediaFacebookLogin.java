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
public class SocialMediaFacebookLogin {
    private AppiumDriver<AndroidElement> driver;

    @BeforeEach
    public void setup() throws IOException {
        //driver = BrowserstackBuilder.buildDriver("SocialMediaFacebookLogin");
        driver = AndroidDriverBuilder.buildDriver();
    }

    @Test
    @DisplayName("it should be able to login")
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
        AndroidElement locationSelector = (AndroidElement) new WebDriverWait(driver,  30).until(
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

        //Click on facebook icon
        //Thread.sleep(30000);
        AndroidElement facebookIcon = (AndroidElement) new WebDriverWait(driver, 50).until(
                //Formula of xpath=> .xpath("//Class name[@attribute name='value']")
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='color-facebook']"))
        );
        facebookIcon.click();


        //click on allow essential and optional cookies
        //Thread.sleep(3000);
        AndroidElement allowCookies = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='u_0_m_r9']"))
        );
        allowCookies.click();

        //Enter facebook email address
        AndroidElement userNameInput = (AndroidElement) new WebDriverWait(driver, 50).until(
                //Formula of xpath=> .xpath("//Class name[@attribute name='value']")
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='m_login_email']"))
        );
        userNameInput.click();
        userNameInput.sendKeys("android.mhikes@gmail.com");

        // insert password
        //Thread.sleep(3000);
        AndroidElement passwordInput = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='m_login_password']"))
        );
        passwordInput.sendKeys("SXX8fFMbA4aqb");

        //click on Log in button
        //Thread.sleep(3000);
        AndroidElement loginButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@text='Log in']"))
        );

        loginButton.click();

        //Enter code the connection for continue
        //Thread.sleep(3000);
        AndroidElement codeConnection = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@resource-id='approvals_code']"))
        );

        codeConnection.sendKeys("21699422");

        //click on submit code
        //Thread.sleep(3000);
        AndroidElement submitcodeButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@resource-id='checkpointSubmitButton-actual-button']"))
        );

        submitcodeButton.click();

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


        //Click on gothrough first hike
        Thread.sleep(3000);
        AndroidElement usernameProfile = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id(packageName + ":id/username_profile"))//text-Trouver ma première balade and class- android.widget.Button
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

    @AfterAll()
    public void tearDown() {
        //if(null != driver) {
           // System.out.println("quiting the driver");
            driver.quit();
        }
    }


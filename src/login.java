import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class login extends base {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//=================================== INITIAL SETUP ========================================
		
		//PhotoMedia Allow Button
	    AndroidElement photoAndMediaAllowButton = driver.findElementById("com.android.permissioncontroller:id/permission_allow_button");
		photoAndMediaAllowButton.click();
      
		//AllowAccess and Close Button
		AndroidElement acceptAndCloseButton = driver.findElementById("com.decathlon.quechuafinder:id/button_agree");	
		acceptAndCloseButton.click();
		
		System.out.println("Cliqué sur Accepter & Fermer");
			
		
        
       //===================== Swipe for beta test ===================
		Thread.sleep(3000);
        WebElement betaPanel1 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout"));
        swipePanel(betaPanel1, driver);
        Thread.sleep(1000);
        WebElement betaPanel2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout"));
        swipePanel(betaPanel2, driver);
        Thread.sleep(1000);
        WebElement betaPanel3 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout"));
        swipePanel(betaPanel3, driver);
		
        //TODO: Add id of Terminé button and click on it to close this panel
		
		//===================== End: Swipe for beta test ===================
		
		//Onboard and procees Button
		Thread.sleep(3000);
        AndroidElement welcomeAgree = driver.findElementById("com.decathlon.quechuafinder:id/onboard_proceed_btn");
        welcomeAgree.click();
                
    	// select location next to you
        Thread.sleep(3000);
        AndroidElement locationSelector = driver.findElementById("com.android.permissioncontroller:id/permission_location_accuracy_radio_fine");
        locationSelector.click();
        
        // give permission to selected location
        Thread.sleep(3000);
        AndroidElement locationPermissionAllowUsingThisAppButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
        );
        locationPermissionAllowUsingThisAppButton.click();					
		
		
		
		// GoTo Profile Page for Login 
        Thread.sleep(3000);
	    AndroidElement profilePageButton = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/profile_nav_menu"))
	    );
	    profilePageButton.click();
    
	    //click on connect to login page //Continue avec decathlon
	    Thread.sleep(3000);
	    AndroidElement decathlonConnectionButton = (AndroidElement) new WebDriverWait(driver, 30).until(
	            //ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/connect_btn"))
	            ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/btn_decathlon_login"))
	    );
	    decathlonConnectionButton.click();
	    
	    //Insert emailId
	    Thread.sleep(3000);
	    AndroidElement userNameInput = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(
	        		MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View/android.view.View[2]/android.widget.EditText"))
	    );
	    
	    userNameInput.sendKeys("test.decathlonoutdoor@gmail.com");
	    
	        
	    //click on next button
	    Thread.sleep(3000);
	    AndroidElement nextButton = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.Button"))
	    );
	    
	    nextButton.click();
	   
	    // insert password
	    Thread.sleep(3000);
	    AndroidElement passwordInput = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[3]/android.widget.EditText"))
	    );
	    
	    passwordInput.sendKeys("4SG!!7xG");
	    
	    //click on sign in button valider
	    Thread.sleep(3000);
	    AndroidElement signinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.Button"))
	    );
	    
	    signinButton.click();
	    
	    Thread.sleep(3000);
	    @SuppressWarnings("unused")
		AndroidElement usernameprofile = (AndroidElement) new WebDriverWait(driver, 30).until(
	            ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.decathlon.quechuafinder:id/username_profile"))
	    );
	    System.out.println("Login Successfully");
	    //close the driver
	    
	    //driver.close();
	    Thread.sleep(7000);
		
		driver.quit();
	   
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
}

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {
	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
		//File app = new File("/Users/poojasangani/Desktop/missing-id/app-release.apk");
		File app = new File("/Users/poojasangani/Desktop/android apk/4.16.0 adding_missing_ids.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		//here we set our android emulator 
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "PoojaEmulatorPixel5API32");
		//here we set our capability type
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		cap.setCapability("appPackage", "com.decathlon.quechuafinder");
		cap.setCapability("platform", "Android");
		cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
		//cap.setCapability("noReset", "true");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		return driver;
	}	

}

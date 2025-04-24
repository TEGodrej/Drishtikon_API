package Authentication;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class sample {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities dc= new DesiredCapabilities();
//		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("platformName", "android");
		dc.setCapability("deviceName", "sunmiTesting");
		dc.setCapability("automationName", "Uiautomator2");
		dc.setCapability("UDID", "WKAQCYCQKROVDE4L");
//		dc.setCapability("app", "");
		URL url = URI.create("http://localhost:4723").toURL();
		
		AndroidDriver driver=new AndroidDriver(url, dc);
//		driver.installApp("C:\\Users\\testing.engineer\\Downloads\\fms-operator-development-v1.17.22.apk");
//		driver.isAppInstalled();
		
		driver.activateApp("com.godrej.distributorcrm");
	}
}

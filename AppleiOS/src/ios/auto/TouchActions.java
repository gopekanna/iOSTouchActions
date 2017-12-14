package ios.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class TouchActions {

	IOSDriver<WebElement> driver;


	@BeforeMethod
	 public void setUp() throws MalformedURLException {
		// TODO Auto-generated method stub
	
		String xcodeConfigFile = "/Users/gopikannan/Documents/appium/Config.xcconfig";
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformVersion", "11.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("udid", "1316BC63-0731-469C-A58E-CBBFB1743E75");
		capabilities.setCapability("deviceName", "iPhone");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("xcodeConfigFile",xcodeConfigFile);
		capabilities.setCapability("app","com.example.apple-samplecode.UICatalog");
//		capabilities.setCapability("app", "/Users/gopikannan/Library/Developer/Xcode/DerivedData/UICatalog-dvdwxiothahykacfxddjsnekmaui/Build/Products/Debug-iphoneos/UICatalog.app");
		
		driver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		}
	
	@Test(priority = 1)
	public void ScrollWithElement() throws InterruptedException
	{
		RemoteWebElement elements =  (RemoteWebElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Text View']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", ((RemoteWebElement) elements).getId());
		scrollObject.put("toVisible", "true");
		js.executeScript("mobile: scroll", scrollObject);
		Thread.sleep(5000);
	}
	
	@Test(priority = 2)
	public void ScrollWithoutElement() throws InterruptedException
	{
		Dimension size = driver.manage().window().getSize();        
		System.out.println("Screen Size= "+size);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		Thread.sleep(2000);
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);
		Thread.sleep(5000);
	}
	
	@Test(priority = 3)
	public void LongPressWithElement() throws InterruptedException
	{
		TouchAction finger = new TouchAction(driver);
		finger.longPress(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Action Sheets']")));
		finger.perform().release();
		Thread.sleep(5000);
	}
	
	@Test(priority = 4)
	public void LongPressWithCoords() throws InterruptedException
	{
		TouchAction finger = new TouchAction(driver);
		finger.longPress(16,510);
		finger.perform().release();
		Thread.sleep(5000);
	}
	
	@Test(priority = 5)
	public void TapWithElement() throws InterruptedException
	{
		TouchAction finger = new TouchAction(driver);
		finger.tap(driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Image View']"));
		finger.perform().release();
		Thread.sleep(7000);
	}
	
	@Test(priority = 6)
	public void DatePicker() throws InterruptedException
	{
		TouchAction finger = new TouchAction(driver);
		finger.tap(driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Date Picker']"));
		finger.perform().release();
		Thread.sleep(2000);
		RemoteWebElement elements =  (RemoteWebElement) driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='UICatalog']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("order", "next");
		params.put("offset", 0.15);
		params.put("element", ((RemoteWebElement) elements).getId());
		js.executeScript("mobile: selectPickerWheelValue", params);
		Thread.sleep(5000);
	}
	
	@Test(priority = 7)
	public void SwipeSlider() throws InterruptedException
	{
		TouchAction finger = new TouchAction(driver);
		finger.longPress(16,510);
		finger.perform().release();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("duration", 2.0);
		params.put("fromX", 140);
		params.put("fromY", 142);
		params.put("toX", 245);
		params.put("toY", 142);
		js.executeScript("mobile: dragFromToForDuration", params);
		Thread.sleep(5000);
	}
	
		
	@AfterMethod
	public void logout() throws InterruptedException
	{
		driver.quit();
	}
	

}

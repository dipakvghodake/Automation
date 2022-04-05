package com.mmp.scripts;

import java.io.IOException;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mmp_giftcard.libraries.Page1_HomePage;
import com.mmp_giftcard.libraries.Page2_GiftCards;
import com.mmp_giftcard.libraries.Page3_CorporateGiftCard;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TC_02 {
	
	static WebDriver wd;
	 
	static ExtentTest test2; // Interface
	static ExtentReports rep2; // class
	
	static Logger lg2=Logger.getLogger("ActivityLog.class");
	
	Page1_HomePage obj = new Page1_HomePage();
	Page2_GiftCards obj1=new Page2_GiftCards();
	Page3_CorporateGiftCard obj2=new Page3_CorporateGiftCard();
	
	//Cross-Browser Testing
	
	@Parameters("browserName")
	@BeforeTest
	
	public void setup (String browserName) {
		System.out.println("Browser name is :" +browserName);
			
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			wd = new ChromeDriver();
    	     }
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			wd = new FirefoxDriver();
			}
			
		 wd.manage().window().maximize();
		 wd.manage().deleteAllCookies();
	
	rep2 = new ExtentReports(System.getProperty("user.dir") + "./ExtentReport/Reportdeliverable02.html");
	test2 = rep2.startTest("TC02"); // trestNG class
	 
}

@Test(priority=1)
public void demo() throws IOException {

	obj.initdriver1(wd);
	obj.click_GiftCards();
	test2.log(LogStatus.PASS, "The gift card page is opened successfully");
	PropertyConfigurator.configure("./Resources/log4j.properties");
	lg2.info("Testcase is passed .. gift card page is opened");
}

@Test(priority=2)
public void demo2() throws IOException {
	obj1.initdriver2(wd);
	obj1.click_CorporateGiftCard();
	test2.log(LogStatus.PASS, "The corporate gift card page is opened successfully");
	lg2.info("Testcase is passed .. corporate gift card page is opened");
}


@Test(priority=3)
public void demo3() throws IOException {
	obj2.initdriver3(wd);
	obj2.fill_card_details();
	String ss = "C:\\Users\\2108061\\TeamNo8_BookOneWayOutstationCab-2\\BookOneWayOutstationCab\\ScreenShots\\errormsg.png";
	String ss2 = test2.addScreenCapture(ss);
	test2.log(LogStatus.ERROR, "In-valid details entered"+ ss2);
	lg2.info("Testcase is passed .. The details for corporate gift card are entered");
	obj2.display_errorMessage();
	test2.log(LogStatus.INFO, "The error message has been captured");
	lg2.info("Testcase is passed .. The error message is displayed");
	obj2.capture_errorMessage();
	test2.log(LogStatus.WARNING, "Details must be valid");
	lg2.info("Testcase is passed .. The screenshot of error message is captured");
}

@AfterTest
public void close_method() {
	wd.quit();
	rep2.endTest(test2);
	rep2.flush();
	rep2.close(); 
	 
}
}
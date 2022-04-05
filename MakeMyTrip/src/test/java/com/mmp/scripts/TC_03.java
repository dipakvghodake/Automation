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

import com.mmp_hotelbooking.libraries.Page2_HotelBooking;

import com.mmp_cabbooking.libraries.Page1_HomePage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TC_03 {
	static WebDriver wd;
	
	static ExtentTest test1; // Interface
	static ExtentReports rep1; // class
	
	static Logger lg3=Logger.getLogger("ActivityLog.class");
	
	Page1_HomePage obj = new Page1_HomePage();
	Page2_HotelBooking obj2 = new Page2_HotelBooking();
	 
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
		
		rep1 = new ExtentReports(System.getProperty("user.dir") + "./ExtentReport/Reportdeliverable03.html");
		test1 = rep1.startTest("TC03"); // trestNG class
	}
	
	@Test(priority=1)
	public void HomePage() throws IOException {
		
		obj.initdriver1(wd);
		obj.invoke_url();
		test1.log(LogStatus.INFO, "The hotel booking page is opening");
		
		PropertyConfigurator.configure("./Resources/log4j.properties");
		lg3.info("Testcase is passed .. hotel booking page is opened");
		
	}
	
	@Test(priority=2)
	public void Hotel_Booking_Page() throws IOException {
		obj2.initdriver2(wd);
		obj2.clickOnHotelsLinkTest();
		obj2.clickOnRoomsAndGuestsTest();
		
		test1.log(LogStatus.PASS, "The number of adult persons is diplayed on console");
		lg3.info("Testcase is passed .. the number of adult persons is displayed");
	}
	
	@AfterTest
	public void close() {
		wd.quit();
		rep1.endTest(test1);
		rep1.flush();
		rep1.close(); 
	}
}

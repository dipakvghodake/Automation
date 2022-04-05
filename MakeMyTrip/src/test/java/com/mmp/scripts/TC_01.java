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

import com.mmp_cabbooking.libraries.Page1_HomePage;
import com.mmp_cabbooking.libraries.Page2_CabDetails;
import com.mmp_cabbooking.libraries.Page3_CarList;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TC_01 {
	static WebDriver wd;
	
	static ExtentTest test1; // Interface
	static ExtentReports rep1; // class
	
	static Logger lg1=Logger.getLogger("ActivityLog.class");
	
	Page1_HomePage obj = new Page1_HomePage();
	Page2_CabDetails obj1 = new Page2_CabDetails();
	Page3_CarList obj2 = new Page3_CarList();
	
	//Cross-Browser Testing
	
	@Parameters("browserName")
	@BeforeTest
	
	public void setup (String browserName) {
		System.out.println("Browser name is :" +browserName);
			
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			wd = new ChromeDriver();
    	     }
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers/geckodriver.exe");
			wd = new FirefoxDriver();
			} 
			
		 wd.manage().window().maximize();
		 wd.manage().deleteAllCookies();
			
		rep1 = new ExtentReports(System.getProperty("user.dir") + "//ExtentReport/Reportdeliverable01.html");
		test1 = rep1.startTest("TC01"); // trestNG class
	}
	
	@Test(priority=1)
	public void HomePage() throws IOException {
		
		obj.initdriver1(wd);
		obj.invoke_url();
		test1.log(LogStatus.PASS, "The application is launched successfully");
		
		PropertyConfigurator.configure("./Resources/log4j.properties");
		lg1.info("Testcase is passed .. cab booking page is opened");
	}
	
	@Test(priority=2)
	public void CabSearch() throws IOException {
		obj1.initdriver2(wd);
		obj1.cab_search();
		test1.log(LogStatus.INFO, "The datails for the cab are being entered");
		lg1.info("Testcase is passed .. The detais are entered");
	}
	
	@Test(priority=3)
	public void CabsList() throws IOException {
		obj2.initdriver3(wd);
		obj2.car_list();
		test1.log(LogStatus.PASS, "The list of avalaible cabs is displayed on console");
		lg1.info("Testcase is passed .. The list of cabs is displayed"); 
	}
	
	@AfterTest
	public void close_method() {
		wd.quit();
		rep1.endTest(test1);
		rep1.flush();
		rep1.close(); 
		
	}
}
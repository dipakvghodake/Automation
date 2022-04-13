package Scripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Libraries.Page01_Login;
import Libraries.Page02_StockPriceComparison;
import Libraries.Page03_All_Apps_N_Tools;

public class TC_001 {
	WebDriver wd;

	static ExtentTest test2;
	static ExtentReports resp2;
	
	Page01_Login objj = new Page01_Login();
	Page02_StockPriceComparison objj1 = new Page02_StockPriceComparison();
	Page03_All_Apps_N_Tools objj2 = new Page03_All_Apps_N_Tools();
	
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
				
			resp2 = new ExtentReports(System.getProperty("user.dir") + "//ExtentReport/Report.html");
			test2 = resp2.startTest("TC_001"); // trestNG class
		}
	
	@Test(priority=1)
	public void login() throws IOException {
		objj.initdriver1(wd);
		test2.log(LogStatus.INFO,"Driver is Initialized");
		objj.Login_Method();
		test2.log(LogStatus.PASS, "Login Successfully");
	
	}
	
	@Test(priority=2)
	public void s_price() throws IOException {
		objj1.initdriver2(wd);
		test2.log(LogStatus.INFO,"Driver is Initialized");
		objj1.share_price();
		test2.log(LogStatus.PASS, "Comparison of stock price between Portal & Google");
	
	}
	
	@Test(priority=3)
	public void apps_show() throws IOException {
		objj2.initdriver3(wd);
		test2.log(LogStatus.INFO,"Driver is Initialized");
		objj2.apps();
		test2.log(LogStatus.PASS, "Apps present in Office 365 are dispalyed on console");
		objj2.company();
		test2.log(LogStatus.PASS, "Apps present in Company Apps are dispalyed on console");
		
	}
	@AfterTest
	public void close() {
		resp2.endTest(test2);
	    resp2.flush();
	    resp2.close();
		wd.quit();
	}
	
}

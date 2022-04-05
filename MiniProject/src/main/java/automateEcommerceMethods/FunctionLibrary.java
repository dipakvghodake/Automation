package automateEcommerceMethods;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class FunctionLibrary {
	WebDriver wd;
	Properties p = new Properties();
	String path1 = "C:\\Users\\2108061\\MiniProject\\Object_Repository\\Locators.properties";
	
/****************************

Method Name : getURL()
Method Descrption:
1.To open URL
Author : Dipak Ghodake
EmpID : 2108061
		 	
***************************/
	public void getURL() {
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.manage().deleteAllCookies();
		wd.get("http://automationpractice.com/index.php");
		try {
			Thread.sleep(1500); //To stop the execution of current thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
/****************************

Method Name : go_to_homepage()
Method Descrption:
2. To sign-in, click Sign-in button & enter inputs from properties file.
Author : Dipak Ghodake
EmpID : 2108061
			 	
***************************/	
	public void go_to_homepage() throws InterruptedException, IOException {
		
		//Click on Sign-in
		//To find webelement, we used findElement(Byxpath)
		
		FileReader f2 = new FileReader(path1);
		p.load(f2);
		wd.findElement(By.xpath(p.getProperty("signin_btn"))).click();
		Thread.sleep(1500);
		
		//create account using dummy EmailID
		//To find WebElement for email, we use findelement(By.id)
		wd.findElement(By.id("email_create")).sendKeys("miniprojectdemo1@gmail.com");
		Thread.sleep(1500);
		
		wd.findElement(By.id("SubmitCreate")).click();
				
	}

/****************************

Method Name : WomenTab()
Method Descrption:
3. To move over women tab & select 't-shirts' option.
Author : Dipak Ghodake
EmpID : 2108061
				 	
***************************/	
	 
	public void WomenTab() throws InterruptedException, IOException{
		
		//Move cursor over Women tab
		//Actions class used to perform action like drag and drop, mouse hover.
		//build() use to create chain of action which is to be perform
		//perform() used to execute chain of action
		//moveToElement used to move the mouse to specified element
		//click on sub menu 'T-shirts'.
		FileReader f2 = new FileReader(path1);
		p.load(f2);
		WebElement womentab = wd.findElement(By.xpath(p.getProperty("women_tab")));
		
		Actions action = new Actions(wd);
		action.moveToElement(womentab).build().perform();
		
		Thread.sleep(1500);
		
		WebElement shirts = wd.findElement(By.xpath(p.getProperty("tshirt_btn")));
		shirts.click();
		
	}
	
/****************************

Method Name : selectmorebtn()
Method Descrption:
4. To click 'More' button after hovering on product.
Author : Dipak Ghodake
EmpID : 2108061
			 	
***************************/
	
	public void selectmorebtn() throws InterruptedException, IOException {
		
		FileReader f2 = new FileReader(path1);
		p.load(f2);
		
		//Mouse hover on the first product displayed.
		
		WebElement firstproduct = wd.findElement(By.xpath(p.getProperty("product_hover")));
		Actions action2 = new Actions(wd);
		action2.moveToElement(firstproduct).build().perform();
		Thread.sleep(1500);
		
		//'More' button will be displayed, click on 'More' button.
		
		WebElement morebtn = wd.findElement(By.xpath(p.getProperty("more_btn")));
		morebtn.click();
	}

/****************************

Method Name : select_product_details()
Method Descrption:
5. Select Product Details by choosing quantity,size and color
Author : Dipak Ghodake
EmpID : 2108061
				 	
***************************/
	
	public void select_product_details() throws InterruptedException, IOException {
		
		FileReader f2 = new FileReader(path1);
		p.load(f2);
		
		//To Increase quantity to 2.
		wd.findElement(By.xpath(p.getProperty("quantity_increase"))).click();
		Thread.sleep(1500);
		
		//To Select size 'L'
		Select drpdown = new Select(wd.findElement(By.id("group_1")));
		drpdown.selectByIndex(2);
		Thread.sleep(1500);
		
		//To Select different colour.
		wd.findElement(By.id("color_14")).click();
		Thread.sleep(1500);
		
		//To Click 'Add to Cart' button.
		wd.findElement(By.name("Submit")).click();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Proceed to next option
		wd.findElement(By.xpath(p.getProperty("proceed_section")));
		wd.findElement(By.xpath(p.getProperty("proceed_btn"))).click(); //span		
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wd.findElement(By.xpath(p.getProperty("next_btn"))).click(); //span
		//wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
/****************************

Method Name : select_product_details()
Method Descrption:
6. To Fill personal details to create account if email is already registered, error 
will capture & use credentials to sign-in.
Author : Dipak Ghodake
EmpID : 2108061
					 	
***************************/
	public void account_details() throws IOException, InterruptedException {
		
		//Taking input from properties file
		//FileReader class is used to read data from properties file
		//p.load() use to load file
		//getProperty() returns the system property by passing key
		String path = "C:\\Users\\2108061\\MiniProject\\InputFile\\input.properties";
		FileReader f1 = new FileReader(path);
		p.load(f1);
		
		//Enter EmailID from properties file
		wd.findElement(By.id("email_create")).sendKeys(p.getProperty("em"));
		Thread.sleep(1500);
		
		//After entering EmailID click on create an account button
		wd.findElement(By.id("SubmitCreate")).click();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//error//
		wd.findElement(By.id("create_account_error"));
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement msg = wd.findElement(By.xpath("//*[@id='create_account_error']/ol"));
		if(msg.isDisplayed()) {
			
			//To capture Alert Message
			TakesScreenshot ts1 = (TakesScreenshot) wd;
			File sc = ts1.getScreenshotAs(OutputType.FILE);
			File dc = new File("C:\\Users\\2108061\\MiniProject\\ScreenImages\\error.png");
			Files.copy(sc, dc);
			
			wd.findElement(By.id("email")).sendKeys(p.getProperty("em"));
			Thread.sleep(1500);
			wd.findElement(By.id("passwd")).sendKeys(p.getProperty("password"));
			Thread.sleep(1500);
			wd.findElement(By.id("SubmitLogin")).click();	
		}else {
			
			//Select radio button
			wd.findElement(By.xpath("//form[@id='account-creation_form']"));
			wd.findElement(By.name("id_gender")).click();
			Thread.sleep(1500);
			
			//Enter First Name
			wd.findElement(By.id("customer_firstname")).sendKeys(p.getProperty("firstname"));
			Thread.sleep(1500);
			
			////To enter input in next field, we used sendKeys(Keys.TAB)
			wd.findElement(By.id("customer_firstname")).sendKeys(Keys.TAB);	
			//Enter Last Name
			wd.findElement(By.id("customer_lastname")).sendKeys(p.getProperty("lastname"));
			Thread.sleep(1500);
			
			wd.findElement(By.id("customer_lastname")).sendKeys(Keys.TAB);		
			wd.findElement(By.id("email")).sendKeys(Keys.TAB);
			Thread.sleep(1500);
			
			//Enter password
			wd.findElement(By.id("passwd")).sendKeys(p.getProperty("password"));
			Thread.sleep(1500);
			
			//To select data from dropdown we use Select class
			Select days = new Select(wd.findElement(By.id("days")));
			days.selectByIndex(26); //SelectByIndex use to select data using index value
			Thread.sleep(1500);
			
			Select months = new Select(wd.findElement(By.id("months")));
			months.selectByIndex(9);
			Thread.sleep(1500);
			
			Select years = new Select(wd.findElement(By.id("years")));
			years.selectByIndex(24);
			Thread.sleep(1500);
			
			wd.findElement(By.id("newsletter")).click();
			Thread.sleep(1500);
			
			wd.findElement(By.id("optin")).click();
			Thread.sleep(1500);
			
			//Enter company name
			wd.findElement(By.id("company")).sendKeys(p.getProperty("companyname"));
			Thread.sleep(1500);
			
			//Enter address1
			wd.findElement(By.id("company")).sendKeys(Keys.TAB);		
			wd.findElement(By.id("address1")).sendKeys(p.getProperty("address1"));
			Thread.sleep(1500);
			
			//Enter address2
			wd.findElement(By.id("address1")).sendKeys(Keys.TAB);
			wd.findElement(By.id("address2")).sendKeys(p.getProperty("address2"));
			Thread.sleep(1500);
			
			wd.findElement(By.id("address2")).sendKeys(Keys.TAB);
			//Enter city
			wd.findElement(By.id("city")).sendKeys(p.getProperty("city"));
			Thread.sleep(1500);
			
			//Select State
			Select State = new Select(wd.findElement(By.id("id_state")));
			State.selectByIndex(2);
			Thread.sleep(1500);
			
			//Enter Zipcode
			wd.findElement(By.id("postcode")).sendKeys(p.getProperty("zipcode"));
			Thread.sleep(1500);
			
			//Enter Text Message
			wd.findElement(By.id("other")).sendKeys("New Account");
			Thread.sleep(1500);
			
			wd.findElement(By.id("other")).sendKeys(Keys.TAB);	
			//Enter HomeNumber
			wd.findElement(By.id("phone")).sendKeys(p.getProperty("homephone"));
			Thread.sleep(1500);
			
			wd.findElement(By.id("phone")).sendKeys(Keys.TAB);		
			//Enter MobileNumber
			wd.findElement(By.id("phone_mobile")).sendKeys(p.getProperty("mobilephone"));
			Thread.sleep(1500);
			
			wd.findElement(By.id("phone_mobile")).sendKeys(Keys.TAB);
			//Enter Future Address
			wd.findElement(By.id("alias")).sendKeys(p.getProperty("futureaddress"));
			Thread.sleep(1500);
			
			wd.findElement(By.id("submitAccount")).click();
			
		}
		
		
	}
	
/****************************

Method Name : order_process()
Method Descrption:
7. Steps to confirm order.
Author : Dipak Ghodake
EmpID : 2108061
						 	
***************************/	
	public void order_process() throws InterruptedException, IOException {
		//Enter Text message
		wd.findElement(By.name("message")).sendKeys("This is for miniproject purpose!");
		Thread.sleep(1500);
		
		//Click to proceed
		wd.findElement(By.name("processAddress")).click();
		Thread.sleep(1500);
		
		//Click to Checkbox
		wd.findElement(By.id("cgv")).click();
		Thread.sleep(1500);
		
		wd.findElement(By.name("processCarrier")).click();
		Thread.sleep(1500);
		
		//select payment method
		wd.findElement(By.xpath("//a[@title='Pay by check.']")).click();
		Thread.sleep(1500);
		
		wd.findElement(By.xpath("//*[@id='cart_navigation']/button")).click();
		Thread.sleep(1500);
		
		
		String msg = wd.findElement(By.xpath("//*[@id='center_column']/p[1]")).getText();
		
		//if message contains "complete" word, "Product ordered Successfully" message will display
		if(msg.contains("complete")) {
			TakesScreenshot ts1 = (TakesScreenshot) wd;
			File sc1 = ts1.getScreenshotAs(OutputType.FILE);
			File dc1 = new File("C:\\Users\\2108061\\MiniProject\\ScreenImages\\OrderSuccess.png");
			Files.copy(sc1, dc1);
			
			System.out.println("Product ordered successfully");
		}
		else {
			System.out.println("Incomplete");
		}
		
	}
	
/****************************

Method Name : wait_method()
Method Descrption:
8. To wait for paticular time.
Author : Dipak Ghodake
EmpID : 2108061
							 	
***************************/
	public void wait_method() {
		//To wait for a certain amount of time before it throws a "No such Element"
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
/****************************

Method Name : close_browser()
Method Descrption:
9. To close the browser.
Author : Dipak Ghodake
EmpID : 2108061
								 	
***************************/
	public void close_browser() {
		wd.close();
	}

}

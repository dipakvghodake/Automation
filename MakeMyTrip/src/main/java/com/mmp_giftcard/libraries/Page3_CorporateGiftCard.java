package com.mmp_giftcard.libraries;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

public class Page3_CorporateGiftCard {

	WebDriver wd;
	Logger lg=Logger.getLogger("devpinoyLogger");

	public void initdriver3(WebDriver wd) {
		this.wd=wd;
	}
	
	/*******************************************
	Project Name: Book one way outstation cab and display th lowest charges
	Method Name : fill_card_details()
	Method Description : To fill corporate card details make my trip application
	Author: Hemant Mahalpure
	Date of Creation: 04.03.2022
	********************************************/
    
	public void fill_card_details() throws IOException {
		
		Properties prop = new Properties();

		String fpath1="./ObjectRepositories/Locators_GiftCard.properties";

		FileReader fr1 = new FileReader(fpath1);

		prop.load(fr1);
		
		//To click on email for the communication of corporate gift card
		
		WebElement btn3 = wd.findElement(By.xpath(prop.getProperty("E_mail")));
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("arguments[0].click()", btn3);
		
       //Implicit wait		
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
		//To scroll down for filling card details		
		JavascriptExecutor js2 = (JavascriptExecutor) wd;
    	js2.executeScript("window.scrollBy(0,500)", "");
    	
    	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	//User Details
        wd.findElement(By.xpath(prop.getProperty("RecipientName"))).sendKeys("Rohit Saini");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("RecipientMobNo"))).sendKeys("1237297988");
     	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("RecipientEmail"))).sendKeys("rohitsaini12gmail.com");
     	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("SenderName"))).sendKeys("Anand Shukla");
     	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("SenderMobNo"))).sendKeys("3482856219");
     	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("SenderEmail"))).sendKeys("anandshukla34@gmail.com");
     	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     	
     	wd.findElement(By.xpath(prop.getProperty("BuyNow"))).click();
    	 
    	}
    	

	/*******************************************
	Project Name: Book one way outstation cab and display th lowest charges
	Method Name : display_errorMessage()
	Method Description : To display message on console after entering  invalid mail
	Author: Hemant Mahalpure
	Date of Creation: 04.03.2022
	********************************************/
	
    	public void display_errorMessage() {
    	
    	WebElement el = wd.findElement(By.xpath("//*[@class='red-text font11 append-top5']"));
    	
    	String errormsg = el.getText();
        System.out.println("Error message is: "+ errormsg);
        }
    	
   	/*******************************************
   	Project Name: Book one way outstation cab and display th lowest charges
    Method Name : capture_errorMessage()
    Method Description : To capture screenshot of error message after entering  invalid mail
    Author: Hemant Mahalpure
   	Date of Creation: 04.03.2022
   	********************************************/
    	
        public void capture_errorMessage() {
        	
    	File src = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(src,new File("./Screenshots/errormsg.png"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		lg.debug("Step-6: The error message is dispalyed and captured");
        
        }
		
}


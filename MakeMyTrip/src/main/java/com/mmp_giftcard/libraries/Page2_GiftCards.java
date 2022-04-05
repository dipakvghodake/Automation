package com.mmp_giftcard.libraries;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page2_GiftCards{

    WebDriver wd;
	
    Logger lg=Logger.getLogger("devpinoyLogger");
    /*******************************************
    Project Name: Book one way outstation cab and display th lowest charges
	Method Name : initdriver2(WebDriver wd)
	Method Description : To initialise webdriver 
	Author: Hemant Mahalpure	
    ********************************************/
	public void initdriver2(WebDriver wd) {
		this.wd=wd;
	}
	
	/*******************************************
	Project Name: Book one way outstation cab and display th lowest charges
	Method Name : click_CorporateGiftCard()
	Method Description : To open corporate gift card page in the make my trip application
	Author: Hemant Mahalpure
	Date of Creation: 04.03.2022
	********************************************/
	
	public void click_CorporateGiftCard() throws IOException {
		
		Set<String> windowIDs = wd.getWindowHandles();
		Iterator<String> itr = windowIDs.iterator();
		
		//String mainPageID = itr.next();
		String mainPageID = itr.next();
		String cardPageID = itr.next();
		
		wd.switchTo().window(cardPageID);
		
		Properties prop = new Properties();
		
		String fpath1="./ObjectRepositories/Locators_GiftCard.properties";
		
		FileReader fr1 = new FileReader(fpath1);
		
	    prop.load(fr1);
		
	   //To scroll down for clicking on corporate gift card
	    
		JavascriptExecutor js1 = (JavascriptExecutor) wd;
    	js1.executeScript("window.scrollBy(0,500)", "");
    	
    	wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	/****************** Corporate Gift Card ************************/
    	
    	WebElement btn = wd.findElement(By.xpath(prop.getProperty("CorporateGiftCard")));
		JavascriptExecutor js = (JavascriptExecutor)wd;
		 js.executeScript("arguments[0].click()", btn);
		 
         //Implicit wait
		 
		 wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	
		 lg.debug("Step-5: Corporate gift card page is opened");
         }		
		}



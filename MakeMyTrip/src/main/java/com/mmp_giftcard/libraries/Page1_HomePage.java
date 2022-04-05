package com.mmp_giftcard.libraries;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page1_HomePage {

    WebDriver wd;
    
	public void initdriver1(WebDriver wd) {
		this.wd=wd;
	}
	
	Logger lg=Logger.getLogger("devpinoyLogger");
    
	/*******************************************
	Method Name : click_GiftCards()
	Method Description : To open gift card page in the make my trip application
	Author: Hemant Mahalpure
	Date of Creation: 04.03.2022
	********************************************/
	
public void click_GiftCards() throws IOException {
	
	Properties prop = new Properties();
	
	String fpath1="./ObjectRepositories/Locators_GiftCard.properties";
	
	FileReader fr1 = new FileReader(fpath1);
	
    prop.load(fr1);
	
	wd.get(prop.getProperty("url"));
		
	/****************** Gift Cards ************************/
		
	WebElement btn = wd.findElement(By.xpath(prop.getProperty("GiftCards")));
	JavascriptExecutor js = (JavascriptExecutor)wd;
	js.executeScript("arguments[0].click()", btn);
	
	lg.debug("Step-4: The application has been launched and gift card page is opened");
}
}
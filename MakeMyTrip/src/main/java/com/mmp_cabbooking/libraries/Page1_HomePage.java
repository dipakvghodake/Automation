package com.mmp_cabbooking.libraries;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Page1_HomePage {
	
	WebDriver wd;	
	Logger lg=Logger.getLogger("devpinoyLogger");
	
/****************************

Method Name : initdriver1(WebDriver wd)
Method Descrption: Initialization Of Driver
Author Name : Dipak Ghodake
Date : 12/03/2022
			 	
***************************/
	
	public void initdriver1(WebDriver wd) {
		this.wd=wd;
	}
	
/****************************

Method Name : getURL()
Method Descrption: To open URL
Author Name : Dipak Ghodake
Date : 12/03/2022
 * @throws IOException 
			 	
***************************/
	
	public void invoke_url() throws IOException {
		
		Properties properties = new Properties();		
		String fpath=System.getProperty("user.dir")+"\\ObjectRepositories\\Locators_CabBooking.properties";
		FileReader fr = new FileReader(fpath);		
	    properties.load(fr);
		
		wd.get("https://www.makemytrip.com/");
		wd.findElement(By.xpath(properties.getProperty("clickbtn"))).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lg.debug("Step-1: The application has been launched successfully");
	}
	
}

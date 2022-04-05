package com.mmp_cabbooking.libraries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page2_CabDetails {
	
	WebDriver wd;	
	Logger lg=Logger.getLogger("devpinoyLogger");	
	Properties properties = new Properties();	
	String fpath=System.getProperty("user.dir")+"\\ObjectRepositories\\Locators_CabBooking.properties";
	
/****************************

Method Name : initdriver2(WebDriver wd)
Method Descrption: Initialization Of Driver
Author Name : Dipak Ghodake
Date : 12/03/2022
				 	
***************************/
	
	public void initdriver2(WebDriver wd) {
		this.wd=wd;
	}
	
/****************************

Method Name : cab_serach()
Method Descrption: Entering cab booking details
Author Name : Dipak Ghodake
Date : 12/03/2022
 * @throws IOException 
				 	
***************************/
	
	public void cab_search() throws IOException {
		
		
		FileReader fr = new FileReader(fpath);
		
	    properties.load(fr);
		
		//Click on Cab button
	    WebElement b = wd.findElement(By.xpath(properties.getProperty("CabButton")));
	    JavascriptExecutor js1 = (JavascriptExecutor)wd;
		js1.executeScript("arguments[0].click()", b);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/***************** FROM CITY **************************/
		
		  wd.findElement(By.id("fromCity")).click();
		  
		  wd.findElement(By.xpath(properties.getProperty("fromcity_input"))).sendKeys("Delhi");
		  
		  try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  wd.findElement(By.xpath(properties.getProperty("DelhiCity"))).click();
		  
		  
		/****************** TO CITY ***************************/
		  
		  WebElement btn = wd.findElement(By.id("toCity"));
		  
		  /*To avoid 'Element Click Intercepted Exception', we have used JavascriptExecutor */
		  JavascriptExecutor js = (JavascriptExecutor)wd;
		  js.executeScript("arguments[0].click()", btn);
		  
		  wd.findElement(By.xpath(properties.getProperty("tocity_input"))).sendKeys("Manali");
		  
		  try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  //it will select Manali city from list
		  wd.findElement(By.id(properties.getProperty("ManaliCity"))).click();
		  
		  try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
		/****************** DEPARTURE DATE***********************/
		  
		  WebElement btn2 = wd.findElement(By.xpath(properties.getProperty("DateButton")));
		  js.executeScript("arguments[0].click()", btn2);
		  
		  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  WebElement btn3 = wd.findElement(By.xpath(properties.getProperty("SelectDate")));
		  js.executeScript("arguments[0].click()", btn3);
		  
		  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		/****************** Time ************************/
		  WebElement time_btn = wd.findElement(By.xpath(properties.getProperty("TimeButton")));
		  js.executeScript("arguments[0].click()", time_btn);

		  //It scrolls list of time
		  WebElement drpdwn = wd.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div[2]/div[1]/div[5]/ul"));
		  js.executeScript("window.scrollBy(0,200)", drpdwn);
		  
		  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  WebElement select_btn = wd.findElement(By.xpath(properties.getProperty("SelectTime")));
		  js.executeScript("arguments[0].click()", select_btn);
		  
		  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		// Click on search
		  wd.findElement(By.xpath(properties.getProperty("SearchBtn"))).click();
		 
		  lg.debug("Step-2: The details for the cab are entered successfully");
		  
	}

}

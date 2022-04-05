package com.mmp_hotelbooking.libraries;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Page2_HotelBooking {
	
	WebDriver wd;
    

    Logger lg=Logger.getLogger("devpinoyLogger");
    
    Properties properties = new Properties();
	
    String fpath="./ObjectRepositories/Locators_HotelBooking.properties";
 
    public void initdriver2(WebDriver wd) {
		this.wd=wd;
	}
  
	/*******************************************
        Project Name: Book one way outstation cab and display th lowest charges
    	Method Name : clickOnHotelsLinkTest()
		Method Description : To click on Hotels to access the page
		Author:Pratik Sakrate
	 ********************************************/
	 
	public void clickOnHotelsLinkTest() throws IOException {
		
		FileReader fr = new FileReader(fpath);
		properties.load(fr);
		
		WebElement hotels = wd.findElement(By.xpath(properties.getProperty("Hotels")));
	    hotels.click(); 
		  
		 wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
	  }
	
	/*******************************************
    Project Name: Book one way outstation cab and display th lowest charges
	Method Name : clickOnRoomsAndGuestsTest()
	Method Description : To click on Rooms and Guests to print the number of adult persons
	Author: Pratik Sakrate
    ********************************************/
	
	public void clickOnRoomsAndGuestsTest() throws IOException {
		  WebElement roomsAndAdults = wd.findElement(By.xpath(properties.getProperty("Rooms&Guests")));
		  roomsAndAdults.click();
		  
		  String fnm = System.getProperty("user.dir") + "\\ExcelSheet\\HotelData\\TotalAdults.xls";
		  HSSFWorkbook wb = new HSSFWorkbook();
		  HSSFSheet st = wb.createSheet("Total Adults");
		  int rownum=1;
		
		  //To print the number of adult persons
		  
		List <WebElement> lists =wd.findElements(By.xpath(properties.getProperty("NumberofAdults"))); 
		System.out.println("The number of adults are as:");
		for(WebElement num : lists) {
			String text = num.getText();
			System.out.println(text);
			
			
			HSSFRow row0 = st.createRow(0);
			row0.createCell(0).setCellValue("The numbers of Adults are as -");
			
			HSSFRow row1 = st.createRow(rownum++);
			
			row1.createCell(0).setCellValue(text);
		}
		FileOutputStream fileOut = new FileOutputStream(fnm);
		wb.write(fileOut);
		fileOut.close();
		wb.close();
		
		//Implicit Wait
		
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        

		lg.debug("Step-5: The number of adult persons is displayed");
	  }
}

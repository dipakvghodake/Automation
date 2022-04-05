package com.mmp_cabbooking.libraries;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page3_CarList {
	
	WebDriver wd;	
	Logger lg=Logger.getLogger("devpinoyLogger");	
	Properties properties = new Properties();	
	String fpath= System.getProperty("user.dir")+"\\ObjectRepositories\\Locators_CabBooking.properties";

/****************************

Method Name : initdriver3(WebDriver wd)
Method Descrption: Initialization Of Driver
Author Name : Dipak Ghodake
Date : 12/03/2022
					 	
***************************/	
	
	public void initdriver3(WebDriver wd) {
		this.wd=wd;
	}
	
/****************************

Method Name : car_list()
Method Descrption: It will display list of cabs available
Author Name : Dipak Ghodake
Date : 12/03/2022
 * @throws IOException 
					 	
***************************/
	
	public void car_list() throws IOException {
		
		FileReader fr = new FileReader(fpath);
		
	    properties.load(fr);
		
		//Wait untill SUV checkbox is visible
		WebDriverWait wait = new WebDriverWait(wd,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("SUVcheckbox"))));
		
		//Click on SUV checkbox
		WebElement checkbox = wd.findElement(By.xpath(properties.getProperty("SUVcheckbox")));
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("arguments[0].click()", checkbox);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		js.executeScript("window.scrollBy(0,500)", "");
		
		
		//To store data in excel file
		String fnm = System.getProperty("user.dir")+"\\ExcelSheet\\CabBooking\\PriceData.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Price");
		int rownum=1;
		
		//To locate all lowest price
		List <WebElement> lists = wd.findElements(By.xpath(properties.getProperty("PriceList")));
		String charge = wd.findElement(By.xpath("//*[@id='List']/div[1]/div/div[3]/div/div[2]/div/p")).getText();
		System.out.println("Lowest charges :-" + charge);
	  	for(WebElement num : lists) {
	  		String text = num.getText();
	  		System.out.println(text);
	  		
	  		
	  		HSSFRow row0 = sheet.createRow(0);
	  		row0.createCell(0).setCellValue("Lowest Prices are as - ");
	  		
	  		HSSFRow row1 =sheet.createRow(rownum++);
	  		
	  		row1.createCell(0).setCellValue(text);
	  	}
	  	
	  	FileOutputStream fileOut = new FileOutputStream(fnm);  
	    workbook.write(fileOut);  
	    
	    //closing the Stream  
	    fileOut.close();  
	    
	    //closing the workbook  
	    workbook.close();
	  	
	  	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  	
	  	lg.debug("Step-1: The list of cabs with lowest price is displayed on the console.");
	}
	
}

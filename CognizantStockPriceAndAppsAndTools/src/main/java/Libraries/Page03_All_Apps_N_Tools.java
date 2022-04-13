package Libraries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

public class Page03_All_Apps_N_Tools {

	WebDriver wd;
	
	Properties p = new Properties();
	String path= System.getProperty("user.dir")+"\\ObjectRepository\\Locators.properties";
	
	/****************************

	Method Name : initdriver3(WebDriver wd)
	Method Descrption:
	1.To initialize driver
	Author : Hemant Mahalpure
	EmpID : 2108061
			 	
	***************************/
	public void initdriver3(WebDriver wd) {
		this.wd=wd;
	}
	
	/****************************

	Method Name : apps()
	Method Descrption:
	1.This method will return name of apps present in Office 365
	Author : Hemant Mahalpure
			 	
	***************************/
	
	public void apps() throws IOException {
		FileReader fr = new FileReader(path);				
		p.load(fr);
		
		wd.findElement(By.id(p.getProperty("AppsAndTools"))).click();
		
		wd.findElement(By.xpath(p.getProperty("Office_365_Apps"))).click();
		
		String fnm = System.getProperty("user.dir")+"\\ExcelSheet\\Office365.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Apps");			//create sheet
		int rownum=1;
		
		List <WebElement> lists = wd.findElements(By.className("apps-and-tools__card__title"));
		System.out.println("Apps present in OFFICE 365 :-");
	  	for(WebElement num : lists) {
	  		String text = num.getText();
	  		System.out.println(text);
	  		
	  		//create 1st row
	  		HSSFRow row0 = sheet.createRow(0);
	  		
	  		//It will store below text value at 1st row
	  		row0.createCell(0).setCellValue("Apps present in OFFICE 365 - ");
	  		
	  		//Create rows equal to number of apps
	  		HSSFRow row1 =sheet.createRow(rownum++);
	  		
	  		row1.createCell(0).setCellValue(text);
	  	}
	  	FileOutputStream fileOut = new FileOutputStream(fnm);  
	    workbook.write(fileOut);  
	    
	    //closing the Stream  
	    fileOut.close();  
	    
	    //closing the workbook  
	    workbook.close();
	    
	  
	}
	
	/****************************

	Method Name : company()
	Method Descrption:
	1.This method will return name of apps in Company Apps
	Author : Hemant Mahalpure
			 	
	***************************/
	
	public void company() {
		wd.findElement(By.xpath(p.getProperty("CompanyApps"))).click();
		
		File src = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(src,new File("./ScreenShots/Apps.png"));
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		//div[class='user-content__body']
		//EventFiringWebDriver event = new EventFiringWebDriver(wd);
		//event.executeScript("document.querySelector('div[class=\"user-content__body\"]').scrollHeight"); mCSB_draggerRail
		List <WebElement> lists2 = wd.findElements(By.className("apps-and-tools__card__title"));
		System.out.println("Company Apps:-");
	  	for(WebElement num : lists2) {
	  		String text2 = num.getText();
	  		System.out.println(text2);
	  	}
		
	}
}

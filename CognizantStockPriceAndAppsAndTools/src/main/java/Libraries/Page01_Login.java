package Libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Page01_Login {
	WebDriver wd;
	
	Properties p = new Properties();
	String path= System.getProperty("user.dir")+"\\ObjectRepository\\Locators.properties";
	
	/****************************

	Method Name : initdriver1L(WebDriver wd)
	Method Descrption:
	1.To initialize driver
	Author : Dipak Ghodake
	EmpID : 2108061
			 	
	***************************/
	
	public void initdriver1(WebDriver wd) {
		this.wd=wd;
	}
	
	/****************************

	Method Name : Login_Method()
	Method Descrption:
	1.Login to Be.Cognizant Portal
	Author : Dipak Ghodake
	EmpID : 2108061
			 	
	***************************/
	
	public void Login_Method() throws IOException {
		wd.get("https://be.cognizant.com/");
		Scanner sc = new Scanner(System.in);
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//To read properties file
		FileReader fr = new FileReader(path);
		
	    p.load(fr);
	    
	    String path = System.getProperty("user.dir")+"\\ExcelSheet\\TestData.xlsx";
		
	    FileInputStream file = new FileInputStream(path);
	    
	    XSSFWorkbook wb = new XSSFWorkbook(file);
	    XSSFSheet sheet = wb.getSheet("Sheet1");
	    XSSFRow row = sheet.getRow(1);
	    XSSFCell cell = row.getCell(0);
	    XSSFCell cell1 = row.getCell(1);
	    String value1 = cell.getStringCellValue();
	    String value2 = cell1.getStringCellValue();
	 
	    
	    /*****************************Enter Email ID***************************/
	    
		wd.findElement(By.id(p.getProperty("EmailID"))).sendKeys(value1);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.id(p.getProperty("NextBtn"))).click();
		
		/*****************************Enter Password***************************/
		
		//System.out.println("Enter Password :-");
		//String password = sc.next();
		wd.findElement(By.id(p.getProperty("pwd"))).sendKeys(value2);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.id(p.getProperty("NextBtn"))).click();
		
		try {
			Thread.sleep(1400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*****************************Enter OTP***************************/
		
		wd.findElement(By.xpath(p.getProperty("otp_via_text"))).click();
		System.out.println("Enter OTP :-");
		String OTP = sc.next();
		wd.findElement(By.id(p.getProperty("otp_input"))).sendKeys(OTP);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.id(p.getProperty("submit"))).click();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wd.findElement(By.id(p.getProperty("btn"))).click();
		
		File src = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(src,new File("./ScreenShots/HomePage.png"));
		} catch (IOException e) {
		
			e.printStackTrace();
		
		/*************************To get user information*************************/
		
		String userinfo = wd.findElement(By.xpath(p.getProperty("user_details"))).getText();
		System.out.println(userinfo);
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	}
}

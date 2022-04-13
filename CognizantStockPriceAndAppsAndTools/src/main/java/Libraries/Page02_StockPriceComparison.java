package Libraries;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Page02_StockPriceComparison {
	WebDriver wd;
	
	Properties p = new Properties();
	String path= System.getProperty("user.dir")+"\\ObjectRepository\\Locators.properties";

	/****************************

	Method Name : initdriver2(WebDriver wd)
	Method Descrption:
	1.To initialize driver
	Author : Dipak Ghodake
	EmpID : 2108061
			 	
	***************************/
	
	public void initdriver2(WebDriver wd) {
		this.wd=wd;
	}
	
	/****************************

	Method Name : share_price()
	Method Descrption:
	1.It will compare stock price between portal & google
	Author : Dipak Ghodake
	EmpID : 2108061
			 	
	***************************/
	
	public void share_price() throws IOException {
		
		FileReader fr = new FileReader(path);		
	    p.load(fr);
		
		String stockprice1 = wd.findElement(By.className("stock-ticker-header__price")).getText();
		System.out.print(stockprice1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//It will open new tab
		((JavascriptExecutor)wd).executeScript("window.open()");
		
		ArrayList<String> tabs = new ArrayList<String>(wd.getWindowHandles());
		wd.switchTo().window(tabs.get(1));								//Switch to new tab
		wd.get("https://google.com/");									//Open Google in new Tab
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.findElement(By.name("q")).sendKeys("Cognizant");				//Search Cognizant
		wd.findElement(By.name("q")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String stockprice2 = wd.findElement(By.xpath(p.getProperty("stockprice"))).getText();
		stockprice2 = stockprice2.replace("$", ""); 					//replace $ with null value
		System.out.println(stockprice2);
		wd.switchTo().window(tabs.get(0));								//Switch to parent tab
		
		//Comparison of stock price
		if(stockprice1.equals(stockprice2)) {
			System.out.println("Stock Price in portal is same as on Google");
		}else {
			System.out.print("Stock Price are mismatch!");
		}
		
	}

}

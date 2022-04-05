package automateEcommerceScripts;

import java.io.IOException;

import automateEcommerceMethods.FunctionLibrary;

public class TestExecution {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		FunctionLibrary obj = new FunctionLibrary();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dipak\\eclipse-workspace\\Mini\\driver\\chromedriver.exe");
				
		obj.getURL();
		obj.wait_method();
		obj.go_to_homepage();
		obj.wait_method();
		obj.WomenTab();
		obj.wait_method();
		obj.selectmorebtn();
		obj.wait_method();
		obj.select_product_details();
		obj.wait_method();
		obj.account_details();
		obj.wait_method();
		obj.order_process();
		obj.close_browser();
	}

}

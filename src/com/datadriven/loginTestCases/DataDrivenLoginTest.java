package com.datadriven.loginTestCases;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.datadriven.browserHelper.BrowserHelper;
import com.datadriven.learnVern.DataDrivenReadWriteFile;
import com.datadriven.loginUtil.DataDrivenLoginFactory;

import junit.framework.Assert;

public class DataDrivenLoginTest {
	BrowserHelper browser;
	DataDrivenReadWriteFile excelSheet;
	DataDrivenLoginFactory factory;
	@BeforeTest
	public void beforeTest() throws Exception{
		browser=new BrowserHelper();
		excelSheet=new DataDrivenReadWriteFile();
		
	}
	@Test
	public void loginVerify() throws Exception {
		excelSheet.DataDrivenReadFile("C:\\Users\\Sagor\\eclipse-workspace", "datadriven.xlsx", "Sheet1");
		Sheet sheet=excelSheet.sheet;
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		for(int i=1; i<=rowCount; i++) {
			WebDriver driver=browser.startBrowser("firefox", "https://www.facebook.com");
			String email=excelSheet.getCellData(i, 0);
			String password=excelSheet.getCellData(i, 1);
			factory=PageFactory.initElements(driver, DataDrivenLoginFactory.class);
			factory.DataDrivenLoginTest(email, password);
			//Assert.assertSame("Facebook – log in or sign up", driver.getTitle();
			String title=driver.getTitle();
			System.out.println(title);
			if(title.equalsIgnoreCase("Log in to Facebook | Facebook")) {
				excelSheet.DataDrivenWriteFile(i, 3, "Login Failed");
				excelSheet.DataDrivenWriteFile(i, 4, "Passed");
			}
			else if(title.equalsIgnoreCase("Facebook")) {
				excelSheet.DataDrivenWriteFile(i, 3, "Login Successfull");
				excelSheet.DataDrivenWriteFile(i, 4, "Passed");
			}
			
			driver.quit();
		}
	}
	
	

}

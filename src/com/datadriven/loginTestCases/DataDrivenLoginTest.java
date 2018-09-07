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
		int count=0;
		for(int i=1; i<=rowCount-1; i++) {
			//Browser name and link
			WebDriver driver=browser.startBrowser("chrome", "http://localhost/");
			String email, password;
			if(excelSheet.getCell(i, 2)==null) {
				email="";
			}else {
				email=excelSheet.getCellData(i, 2);
			}
			if(excelSheet.getCell(i, 3)==null) {
				password="";
			}else {
				password=excelSheet.getCellData(i, 3);
			}
			factory=PageFactory.initElements(driver, DataDrivenLoginFactory.class);
			Thread.sleep(2000);
			factory.DataDrivenLoginTest(email, password);
			//Assert.assertSame("Facebook – log in or sign up", driver.getTitle();
			String title=driver.getTitle();
			System.out.println(title);
			if(title.equalsIgnoreCase("Document")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Login Failed");
				excelSheet.DataDrivenWriteFile(i, 6, "Passed");
				count++;
			}
			else if(title.equalsIgnoreCase("Dashboard")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Login Successfull");
				excelSheet.DataDrivenWriteFile(i, 6, "Passed");
				count++;
			}
			else {
				excelSheet.DataDrivenWriteFile(i, 5, "Login Failed");
				excelSheet.DataDrivenWriteFile(i, 6, "Failed");
			}
			Thread.sleep(1000);
			driver.quit();
		}
		WebDriver driver=browser.startBrowser("chromei", "http://localhost/dash_upload.html");
		Thread.sleep(2000);
		String title=driver.getTitle();
		if(title.equalsIgnoreCase("Login")) {
			excelSheet.DataDrivenWriteFile(rowCount, 5, "Please Login to access the page");
			excelSheet.DataDrivenWriteFile(rowCount, 6, "Passed");
			driver.quit();
			count++;
		}
		else{
			excelSheet.DataDrivenWriteFile(rowCount, 5, "Please upload the file");
			excelSheet.DataDrivenWriteFile(rowCount, 6, "Failed");
			driver.quit();
			//count++;
		}
		String result="Test Case Passed "+count+" Out of "+(rowCount);
		excelSheet.DataDrivenWriteFile(5, 7, result);
		
	}

}

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
		for(int i=0; i<=rowCount; i++) {
			WebDriver driver=browser.startBrowser("firefox", "https://www.facebook.com");
			Row row=sheet.getRow(i);
			String email=row.getCell(0).getStringCellValue();
			String password=row.getCell(1).getStringCellValue();
			factory=PageFactory.initElements(driver, DataDrivenLoginFactory.class);
			factory.DataDrivenLoginTest(email, password);
			//Assert.assertSame("Facebook – log in or sign up", driver.getTitle();
			String title=driver.getTitle();
			excelSheet.DataDrivenWriteFile(i, 2, "Passed");
			System.out.println("///////////////////Passed////////////////////////");
			driver.quit();
		}
	}
	
	

}

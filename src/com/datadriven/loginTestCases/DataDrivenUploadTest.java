package com.datadriven.loginTestCases;


import java.io.File;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.datadriven.browserHelper.BrowserHelper;
import com.datadriven.learnVern.DataDrivenReadWriteFile;
import com.datadriven.loginUtil.DataDrivenUploadFactory;

import junit.framework.Assert;

public class DataDrivenUploadTest {
	BrowserHelper browser;
	DataDrivenReadWriteFile excelSheet;
	DataDrivenUploadFactory factory;
	@BeforeTest
	public void beforeTest() throws Exception{
		browser=new BrowserHelper();
		excelSheet=new DataDrivenReadWriteFile();
		
	}
	@Test
	public void uploadVerify() throws Exception {
		excelSheet.DataDrivenReadFile("C:\\Users\\Sagor\\eclipse-workspace", "datadriven.xlsx", "Sheet2");
		Sheet sheet=excelSheet.sheet;
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		int count=0;
		//For doc file
		System.out.println(rowCount);
		for(int i=1; i<=rowCount-2; i++) {
			//Browser name and link
			WebDriver driver=browser.startBrowser("chrome", "http://localhost/dash_upload.html");
			String filePath;
			if(excelSheet.getCell(i, 2)==null) {
				filePath="";
			}else {
				filePath=excelSheet.getCellData(i, 2);
			}
			factory=PageFactory.initElements(driver, DataDrivenUploadFactory.class);
			Thread.sleep(2000);
			factory.DataDrivenUploadTest(filePath);
			File uploadedFile=new File(filePath);
			double uploadedFileSize = uploadedFile.length(); //Size in byte
			//Assert.assertSame("Facebook � log in or sign up", driver.getTitle();
			String title=driver.getTitle();
			System.out.println(title);
			if(uploadedFileSize > 4194304.0 && title.equalsIgnoreCase("Information")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Uploaded");
				excelSheet.DataDrivenWriteFile(i, 6, "Failed");
				//count++;
			}
			else if(uploadedFileSize > 4194304.0 && title.equalsIgnoreCase("Dashboard")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Uploaded n");
				excelSheet.DataDrivenWriteFile(i, 6, "Passed");
				count++;
			}
			else if(title.equalsIgnoreCase("Information")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Uploaded");
				excelSheet.DataDrivenWriteFile(i, 6, "Passed");
				count++;
			}
			else {
				excelSheet.DataDrivenWriteFile(i, 5, "Not Uploaded");
				excelSheet.DataDrivenWriteFile(i, 6, "Failed");
			}
			Thread.sleep(1000);
			driver.quit();
		}
		// for pdf file
		for(int i=3; i<=rowCount; i++) {
			//Browser name and link
			WebDriver driver=browser.startBrowser("chrome", "http://localhost/dash_upload.html");
			String filePath;
			if(excelSheet.getCell(i, 2)==null) {
				filePath="";
			}else {
				filePath=excelSheet.getCellData(i, 2);
			}
			factory=PageFactory.initElements(driver, DataDrivenUploadFactory.class);
			Thread.sleep(2000);
			factory.DataDrivenUploadTest(filePath);
			//Assert.assertSame("Facebook � log in or sign up", driver.getTitle();
			String title=driver.getTitle();
			System.out.println(title);
			if(title.equalsIgnoreCase("Information")) {
				excelSheet.DataDrivenWriteFile(i, 5, "Uploaded");
				excelSheet.DataDrivenWriteFile(i, 6, "Failed");
				//count++;
			}
			else {
				excelSheet.DataDrivenWriteFile(i, 5, "Not Uploaded");
				excelSheet.DataDrivenWriteFile(i, 6, "Passed");
				count++;
			}
			Thread.sleep(1000);
			driver.quit();
		}
		System.out.println(count);
		String result="Test Case Passed "+count+" Out of "+(rowCount);
		System.out.println(result);
		excelSheet.DataDrivenWriteFile(3, 7, result);
	}

}

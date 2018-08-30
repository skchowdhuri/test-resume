package com.datadriven.learnVern;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataDrivenExample  {
	public void readExcell(String filePath, String fileName, String sheetName) throws IOException {
		File file=new File(filePath+"\\"+fileName);
		FileInputStream fis=new FileInputStream(file);
		Workbook loginWorkbook=null;
		
		String fileExtension=fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			loginWorkbook=new XSSFWorkbook(fis);
			
		}
		else if(fileExtension.equals(".xls")) {
			loginWorkbook=new HSSFWorkbook(fis);
		}
		Sheet loginSheet=loginWorkbook.getSheet(sheetName);
		int rowCount=loginSheet.getLastRowNum()-loginSheet.getFirstRowNum();
		for (int i=1; i<=rowCount; i++) {
			Row row=loginSheet.getRow(i);
			String username=row.getCell(0).getStringCellValue();
			String password=row.getCell(1).getStringCellValue();
			test(username, password);
			
		}
		
	}
	public void test(String username, String password) {
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys(username);
		//driver.findElement(By.id("identifierNext")).click();
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginbutton")).click();
		driver.quit();
		
	}
	public static void main(String[] args) throws IOException {
		String filePath="F:\\";
		DataDrivenExample readFile=new DataDrivenExample();
		readFile.readExcell(filePath, "datadriven.xlsx", "Sheet1");
	}

}

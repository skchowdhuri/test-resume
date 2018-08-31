package com.datadriven.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WriteData {
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
		WebDriver driver= new FirefoxDriver();
		File file=new File("C:\\Users\\Sagor\\eclipse-workspace\\datadriven.xlsx");
		FileInputStream fis=new FileInputStream(file);
		Workbook workBook=new XSSFWorkbook(fis);
		Sheet sheet=workBook.getSheet("Sheet1");
		Row row=sheet.getRow(0);
		Cell cell=row.createCell(5);
		cell.setCellValue("Hy");
		fis.close();
		FileOutputStream fos=new FileOutputStream(file);
		workBook.write(fos);
		workBook.close();
	}

}

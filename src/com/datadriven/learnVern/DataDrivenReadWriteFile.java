package com.datadriven.learnVern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenReadWriteFile {
//	All access variable is here
	public Workbook workbook;
	public Sheet sheet;
	File file;
	String filePath;
	String fileName;
	String sheetName;
	FileInputStream fis;
	
	
//	To read the file from storage
	public void DataDrivenReadFile(String filePath, String fileName, String sheetName) throws Exception {
		this.filePath=filePath;
		this.fileName=fileName;
		this.sheetName=sheetName;
		
		
		this.file=new File(filePath+"\\"+fileName);
		this.fis=new FileInputStream(file);
		
		String fileExtension=fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			this.workbook=new XSSFWorkbook(fis);
			
		}
		else if(fileExtension.equals(".xls")) {
			this.workbook=new HSSFWorkbook(fis);
		}
		this.sheet=workbook.getSheet(sheetName);
	}
	public String getCellData(int row, int cell) {
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	public void DataDrivenWriteFile(int row, int cell, String result) throws Exception {
		//DataDrivenReadFile(filePath, fileName, sheetName);
		Row tempRow=sheet.getRow(row);
		Cell tempCell=tempRow.createCell(cell);
		tempCell.setCellValue(result);
		fis.close();
		FileOutputStream fos=new FileOutputStream(file);
		workbook.write(fos);
		
	}
	
}

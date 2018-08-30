package com.datadriven.demoOne;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadWriteExcel
{
	WebDriver driver;
	WebDriverWait wait;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFCell cell;
	
 @BeforeTest
 public void TestSetup()
{
	// Set the path of the Firefox driver.
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	// Enter url.
	driver.get("http://www.linkedin.com/");
	driver.manage().window().maximize();
	
	wait = new WebDriverWait(driver,30);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
 
 @Test
 public void ReadData() throws IOException
 {
	 // Import excel sheet.
	 File src=new File("C:\\Users\\Admin\\Desktop\\TestData.xls");
	 
	 // Load the file.
	 FileInputStream finput = new FileInputStream(src);
	 
	 // Load he workbook.
	workbook = new HSSFWorkbook(finput);
	 
     // Load the sheet in which data is stored.
	 sheet= workbook.getSheetAt(0);
	 
	 for(int i=1; i&amp;lt;=sheet.getLastRowNum(); i++)
	 {
		 // Import data for Email.
		 cell = sheet.getRow(i).getCell(1);
		 cell.setCellType(Cell.CELL_TYPE_STRING);
		 driver.findElement(By.id("login-email")).sendKeys(cell.getStringCellValue());
		 
		 // Import data for password.
		 cell = sheet.getRow(i).getCell(2);
		 cell.setCellType(Cell.CELL_TYPE_STRING);
		 driver.findElement(By.id("login-password")).sendKeys(cell.getStringCellValue());
		   		
        }
  } 

}
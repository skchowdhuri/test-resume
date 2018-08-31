package com.datadriven.browserHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHelper {
	WebDriver driver;
	public WebDriver startBrowser(String browserName, String url) {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
		if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("IE")) {
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();	
		driver.get(url);
		return driver;
	}
}

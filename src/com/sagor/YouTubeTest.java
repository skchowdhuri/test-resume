package com.sagor;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.datadriven.browserHelper.BrowserHelper;

public class YouTubeTest {
	BrowserHelper browser;;
	@BeforeTest
	public void beforeTest() throws Exception{
		browser=new BrowserHelper();
	}
	@Test
	public void uploadVerify() throws Exception {
		for(int i=4; i<=20; i++) {
			//Browser name and link
			WebDriver driver=browser.startBrowser("chromei", "https://www.youtube.com/watch?v=T6CPy6qBaG4");
			//String title=driver.getTitle();
			Thread.sleep(470000);
			driver.quit();
		}
	}

}

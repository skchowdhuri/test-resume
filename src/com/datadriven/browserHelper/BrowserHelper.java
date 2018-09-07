package com.datadriven.browserHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserHelper {
	WebDriver driver;
	@SuppressWarnings("deprecation")
	public WebDriver startBrowser(String browserName, String url) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("chromei")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
		}
		else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServe\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browserName.equals("opera")) {
			OperaOptions oo = new OperaOptions();
			oo.setBinary("C:\\Users\\Sagor\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			System.setProperty("webdriver.opera.driver", "C:\\driver-opera\\operadriver.exe");
			driver = new OperaDriver(oo);
		}
		driver.manage().window().maximize();	
		driver.get(url);
		return driver;
	}
}

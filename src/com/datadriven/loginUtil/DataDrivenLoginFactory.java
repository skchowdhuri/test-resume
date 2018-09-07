package com.datadriven.loginUtil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataDrivenLoginFactory {
	@FindBy(id="email")
	WebElement username;
	@FindBy(id="password")
	WebElement password;
	@FindBy(id="icon")
	WebElement loginButtron;
	public void DataDrivenLoginTest(String email, String password) throws Exception {
		this.username.sendKeys(email);
		this.password.sendKeys(password);
		this.loginButtron.click();
		Thread.sleep(2000);
	}
}

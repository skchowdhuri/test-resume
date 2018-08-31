package com.datadriven.loginUtil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataDrivenLoginFactory {
	@FindBy(name="email")
	WebElement username;
	@FindBy(name="pass")
	WebElement password;
	@FindBy(id="loginbutton")
	WebElement loginButtron;
	public void DataDrivenLoginTest(String email, String password) throws Exception {
		this.username.sendKeys(email);
		this.password.sendKeys(password);
		this.loginButtron.click();
		Thread.sleep(1000);
	}
}

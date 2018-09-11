package com.datadriven.loginUtil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataDrivenUploadFactory {
	@FindBy(id="FileUpload1")
	WebElement uploadButton;
	@FindBy(id="upload-btn")
	WebElement submitButton;
//	@FindBy(id="icon")
//	WebElement loginButtron;
	public void DataDrivenUploadTest(String filePath) throws Exception {
		this.uploadButton.sendKeys(filePath);
		//this.password.sendKeys(password);
		Thread.sleep(4000);
		this.submitButton.click();
		Thread.sleep(2000);
	}
}

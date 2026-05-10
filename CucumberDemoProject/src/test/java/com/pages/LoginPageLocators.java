package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {
	@FindBy(xpath = "//input[@placeholder='Username']")
	public  WebElement username;
	@FindBy(xpath = "//input[@placeholder='Password']")
	public  WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	public  WebElement LoginBtn;
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	public WebElement alert;
	
}

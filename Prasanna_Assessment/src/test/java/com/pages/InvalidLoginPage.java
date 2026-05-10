package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvalidLoginPage extends BasePage {
	
	public InvalidLoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()=' Signup / Login']")
	WebElement login;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement lemail;

	@FindBy(xpath = "//input[@data-qa='login-password']")
	WebElement lpass;

	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement btn;
	
	@FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
	WebElement errormsg;
	
	public String geterrormsg() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errormsg));
		return errormsg.getText();
	}
	
	public void invalid(String email, String pass) {
		login.click();
		lemail.sendKeys(email);
		lpass.sendKeys(pass);
		btn.click();
	}
	
}

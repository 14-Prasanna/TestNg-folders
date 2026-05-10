package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
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

	@FindBy(xpath ="//b[text()='Mythily']")
	WebElement username;

	public String getusername() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(username));
		return username.getText();
	}

	public void login(String  email, String pass) {
		login.click();
		lemail.sendKeys(email);
		lpass.sendKeys(pass);
		btn.click();
	}
}

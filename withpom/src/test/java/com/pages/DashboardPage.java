package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {
	
	WebDriverWait wait;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	public WebElement db;
	
	
	public String getPage() {
	        
	     return wait.until(ExpectedConditions.visibilityOf(db)).getText();
	}

}

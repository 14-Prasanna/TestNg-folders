package com.store.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Parameters({"url"})
	@BeforeMethod
	public void beforemethod(String url) {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@AfterMethod
	public void aftermethod() {
		driver.quit();
	}
	
	@Parameters({"search"})
	@Test
	public void search(String search1) {
		WebElement search = driver.findElement(By.xpath("//input[@name = 'search']"));
		search.sendKeys(search1);
		
		WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//a")));
		String searched = product.getText();
		
		if(searched.contains(search1)) {
		    System.out.println( search1 + "keyword is present: " + searched);
		} else {
		    System.out.println(search1 + "keyword is NOT present: " + searched);
		}

	}

}

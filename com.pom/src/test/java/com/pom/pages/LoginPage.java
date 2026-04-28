package com.pom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    WebDriver driver;
    WebDriverWait wait;

    By title = By.xpath("//h5[contains(text(),'Login')]");
    By username = By.name("username");
    By password = By.name("password");
    By loginButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getTitles() {
    	  return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public void setUserName(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
    }

    public void click() {
        driver.findElement(loginButton).click();
    }

    public void login(String user, String pass) {
    	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
         driver.findElement(password).sendKeys(pass);
         driver.findElement(loginButton).click();
    }
}
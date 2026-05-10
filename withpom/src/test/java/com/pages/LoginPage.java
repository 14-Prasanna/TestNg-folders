package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//p[text() = 'Username : Admin']")
    public WebElement title;

    @FindBy(name = "username")
    public WebElement user;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement button;
    
    @FindBy(xpath = "//p[text() = 'Invalid credentials']")
    private WebElement invalid;

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOf(title)).getText();
    }

    public String  loginValid(String user1, String pass) {
        wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(user1);
        password.sendKeys(pass);
        button.click();
        
        return driver.getCurrentUrl();
    }
    
    public String LoginInvalid(String user1, String pass) {
    		wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(user1);
    		password.sendKeys(pass);
    		button.click();
    		
    		return wait.until(ExpectedConditions.visibilityOf(invalid)).getText();
    		
    		
    }
}
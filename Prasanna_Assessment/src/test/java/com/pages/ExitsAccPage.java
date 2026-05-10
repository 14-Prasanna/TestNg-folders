package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExitsAccPage extends BasePage {

    public ExitsAccPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()=' Signup / Login']")
    WebElement login;
    
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement Name;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement Email;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signup;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement existsMsg;

    public void SignUp(String name, String email) {
        login.click();
        Name.sendKeys(name);
        Email.sendKeys(email);
        signup.click();
    }

    public String getExistsMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(existsMsg));
        return existsMsg.getText();
    }
}
package com.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signUpClick;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    public WebElement loginUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement loginPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    public WebElement logout;

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    public WebElement error;

    public String logvalid(String user, String pass) {
        signUpClick.click();
        wait.until(ExpectedConditions.visibilityOf(loginUsername)).sendKeys(user);
        loginPassword.sendKeys(pass);
        loginButton.click();
        return wait.until(ExpectedConditions.visibilityOf(logout)).getText();
    }

    public String Invalid(String user, String pass) {
        signUpClick.click();
        wait.until(ExpectedConditions.visibilityOf(loginUsername)).sendKeys(user);
        loginPassword.sendKeys(pass);
        loginButton.click();
        return wait.until(ExpectedConditions.visibilityOf(error)).getText();
    }
}
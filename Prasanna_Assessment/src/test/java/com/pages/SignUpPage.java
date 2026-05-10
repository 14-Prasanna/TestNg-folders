package com.pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage{


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()=' Signup / Login']")
    WebElement login;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement Name;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement Email;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement Signup;

    @FindBy(xpath = "//input[@type='password']")
    WebElement Password;

    @FindBy(xpath = "//input[@data-qa='first_name']")
    WebElement firstn;

    @FindBy(xpath = "//input[@data-qa='last_name']")
    WebElement lastn;

    @FindBy(xpath = "//input[@data-qa='address']")
    WebElement address;

    @FindBy(xpath = "//input[@data-qa='state']")
    WebElement state;

    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement city;

    @FindBy(xpath = "//input[@data-qa='zipcode']")
    WebElement zipcode;

    @FindBy(xpath = "//input[@data-qa='mobile_number']")
    WebElement mobile;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAcc;
    
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreated;

    @FindBy(xpath ="//a[@data-qa='continue-button']")
    WebElement cont;
    
    @FindBy(xpath ="//a[text()=' Logout']")
    WebElement logout;
    
    public String getCreatedText() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(accountCreated));
        return accountCreated.getText();
    }

    public void signUp(String name, String email, String password, String fname, String lname, String addr, String st, String c, String zip, String mob) {
        login.click();
        Name.sendKeys(name);
        Email.sendKeys(email);
        Signup.click();
        Password.sendKeys(password);
        firstn.sendKeys(fname);
        lastn.sendKeys(lname);
        address.sendKeys(addr);
        state.sendKeys(st);
        city.sendKeys(c);
        zipcode.sendKeys(zip);
        mobile.sendKeys(mob);
        createAcc.click();
    }
    
    public void Continue() {
        cont.click();
    }

    public void Logout() {
        logout.click();
    }
    
}
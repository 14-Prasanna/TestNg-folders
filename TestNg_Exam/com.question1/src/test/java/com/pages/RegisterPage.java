package com.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

public class RegisterPage extends BasePage {

    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signUpClick;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement signUpName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    public WebElement signUpEmail;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    public WebElement signUpButton;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//label[@for='id_gender1']")
    public WebElement mr;

    @FindBy(xpath = "//input[@id='first_name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='last_name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='address1']")
    public WebElement address;

    @FindBy(xpath = "//select[@id='country']")
    public WebElement countryDropdown;

    @FindBy(xpath = "//input[@id='state']")
    public WebElement state;

    @FindBy(xpath = "//input[@id='city']")
    public WebElement city;

    @FindBy(xpath = "//input[@id='zipcode']")
    public WebElement zipcode;

    @FindBy(xpath = "//input[@id='mobile_number']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    public WebElement createAccountBtn;

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    public WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    public WebElement continueBtn;

    @FindBy(xpath = "//b[normalize-space()='Enter Account Information']")
    public WebElement accountInfoHeader;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    public WebElement errorMsg;

    public String RegisterDetails(String name, String email) {
        signUpClick.click();
        wait.until(ExpectedConditions.visibilityOf(signUpName)).sendKeys(name);
        signUpEmail.sendKeys(email);
        signUpButton.click();

        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMsg)).getText();
        } catch (Exception e) {
            return wait.until(ExpectedConditions.visibilityOf(accountInfoHeader)).getText();
        }
    }

    public String AccountCreation(String fName, String pass, String lName, String addr, int index, String st, String ct, String zip, String mobile) {

        wait.until(ExpectedConditions.visibilityOf(mr)).click();
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        password.sendKeys(pass);
        address.sendKeys(addr);

        Select select = new Select(countryDropdown);
        select.selectByIndex(index);

        state.sendKeys(st);
        city.sendKeys(ct);
        zipcode.sendKeys(zip);
        mobileNumber.sendKeys(mobile);

        createAccountBtn.click();
        String result = wait.until(ExpectedConditions.visibilityOf(accountCreatedMsg)).getText();
        continueBtn.click();
        return result;
    }
}
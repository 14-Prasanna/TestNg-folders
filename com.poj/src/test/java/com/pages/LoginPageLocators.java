package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

    @FindBy(name="username")
    public WebElement loginPageUsername;

    @FindBy(name="password")
    public WebElement loginPagePassword;

    @FindBy(xpath="//button[contains(@class,'orangehrm-login-button')]")
    public WebElement click;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    public WebElement invalid;
}
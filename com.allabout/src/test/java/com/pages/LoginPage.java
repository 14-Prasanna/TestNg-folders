package com.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {


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

}

package com.actions;

import com.pages.LoginPageLocators;
import com.utilities.HelpClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginPageAction {

    WebDriverWait wait;

    LoginPageLocators loginPageLocators;

    public LoginPageAction(){
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(HelpClass.getDriver(), loginPageLocators);
        wait = new WebDriverWait(HelpClass.getDriver(), Duration.ofSeconds(20));

    }

    public void setLogin(String strUser, String strpass){

        System.out.println("The values: " + strpass + "\t\t" + strUser);

        wait.until(ExpectedConditions.visibilityOf(loginPageLocators.loginPageUsername));

        loginPageLocators.loginPageUsername.sendKeys(strUser);

        wait.until(ExpectedConditions.visibilityOf(loginPageLocators.loginPagePassword));

        loginPageLocators.loginPagePassword.sendKeys(strpass);

        wait.until(ExpectedConditions.elementToBeClickable(loginPageLocators.click));

        loginPageLocators.click.click();
    }

    public void login() throws FileNotFoundException {
        File file = new File("D:\\TestNG\\com.poj\\src\\test\\resources\\LoginData.properties");

        FileInputStream fileInputStream = null;

        try{
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Properties prop = new Properties();

        try{
            prop.load(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

        setLogin(prop.getProperty("username"), prop.getProperty("password"));

    }

}

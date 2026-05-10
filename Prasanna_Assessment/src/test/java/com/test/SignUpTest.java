package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.SignUpPage;

import utilities.DataProviderClass;

public class SignUpTest {

    WebDriver driver;
    SignUpPage signup;
    
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setup() {
    	
        driver = new ChromeDriver();
        driver.get("https://www.automationexercise.com/");
        log.info("Browser was open");
        driver.manage().window().maximize();
        signup = new SignUpPage(driver);
    }

    @Test(priority = 1, dataProvider = "signupData", dataProviderClass = DataProviderClass.class)
    public void testSignUp(String name, String email, String password,
                          String fname, String lname, String addr,
                          String st, String city, String zip, String mob) {

        signup.signUp(name,email,password,fname,lname,addr,st,city,zip,mob);
        String actual = signup.getCreatedText();
        Assert.assertEquals(actual, "ACCOUNT CREATED!");
        log.info("Successfully Registered");

        signup.Continue();
        signup.Logout();
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
        	log.info("Browser was close");
            driver.quit();
        }
    }
}

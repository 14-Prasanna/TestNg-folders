package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.LoginPage;

import utilities.DataProviderClass;

public class LoginTest {
	WebDriver driver;
    LoginPage Login;
    
    private static final Logger log = LogManager.getLogger(LoginTest.class);
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.automationexercise.com/");
        log.info("Browser was open");
        driver.manage().window().maximize();
        Login = new LoginPage(driver);
    }

    @Test(priority = 3, dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void testLogin(String email, String password) {

        Login.login(email, password);

        String actual = Login.getusername();
        Assert.assertEquals(actual, "Summa");
        log.info("Login Successful");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
        	log.info("Browser was close");
            driver.quit();
        }
    }
}

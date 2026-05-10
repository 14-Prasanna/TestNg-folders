package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.InvalidLoginPage;

import utilities.DataProviderClass;

public class InvalidLoginTest {
	WebDriver driver;
    InvalidLoginPage InLogin;
    
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.automationexercise.com/");
        log.info("Browser was open");
        driver.manage().window().maximize();
        InLogin = new InvalidLoginPage(driver);
    }

    @Test(priority = 4, dataProvider = "invalidLoginData", dataProviderClass = DataProviderClass.class)
    public void testLogin(String email, String password) {

        InLogin.invalid(email, password);

        String actual = InLogin.geterrormsg();
        Assert.assertEquals(actual, "Your email or password is incorrect!");
        log.warn("Invalid login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
        	log.info("Browser was close");
            driver.quit();
        }
    }
}

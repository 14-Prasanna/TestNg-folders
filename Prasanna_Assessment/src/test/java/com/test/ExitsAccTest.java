package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.ExitsAccPage;

import utilities.DataProviderClass;

public class ExitsAccTest {

    WebDriver driver;
    ExitsAccPage page;
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        log.info("Browser was open");
        driver.manage().window().maximize();

        page = new ExitsAccPage(driver);
    }

    @Test(priority = 2, dataProvider = "existingUserData", dataProviderClass = DataProviderClass.class)
    public void verifyExistingEmailError(String name, String email) {

        page.SignUp(name, email);

        String a = page.getExistsMessage();
        Assert.assertEquals(a, "Email Address already exist!");
        log.warn("Already Registered");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
        	log.info("Browser was close");
            driver.quit();
        }
    }
}
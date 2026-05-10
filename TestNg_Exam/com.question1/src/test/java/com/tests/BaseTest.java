package com.tests;

import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import java.time.Duration;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public WebDriverWait wait;

    public static Logger logger = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriver driver1 = new FirefoxDriver();
        driver.set(driver1);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        getDriver().manage().window().maximize();
        getDriver().get("https://automationexercise.com/");
    }

    @AfterMethod
    public void afterMethod() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
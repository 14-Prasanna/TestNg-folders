package com.pom.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() {

        WebDriver localDriver = new FirefoxDriver();
        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.set(localDriver);

        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("Browser opened in Thread: " + Thread.currentThread());
    }

    @AfterMethod
    public void teardown() {
        getDriver().quit();
        driver.remove();
    }
}
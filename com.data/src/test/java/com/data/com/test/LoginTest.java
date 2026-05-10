package com.data.com.test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.data.com.utils.util;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com/");
    }

    @AfterMethod
    public void afterMethod() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "valid", dataProviderClass = util.class)
    public void validation(String email, String pass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword"))).sendKeys(pass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebElement actual_ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String actual = actual_ele.getText();

        Assert.assertTrue(actual.contains("Welcome"), "Login failed, Welcome message not displayed!");

        System.out.println("Login successful");
    }

    @Test(dataProvider = "invalid", dataProviderClass = util.class)
    public void invalid(String email, String pass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword"))).sendKeys(pass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Alert alt = wait.until(ExpectedConditions.alertIsPresent());
        String actual = alt.getText();

        System.out.println("Alert Message: " + actual);

        Assert.assertTrue(actual.contains("Wrong password") || actual.contains("User does not exist"),
                "Unexpected Alert Message: " + actual);

        alt.accept();

        System.out.println("Invalid Username or Password - Alert Verified");
    }
}
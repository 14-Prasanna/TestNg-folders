package com.store.test;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.store.utils.DataProvidersss;

public class LoginTest {
	
	public static Logger logger = LogManager.getLogger(LoginTest.class);

    WebDriver driver;
    WebDriverWait wait;

    @Parameters({ "url" })
    @BeforeMethod
    public void beforeMethod(String url) {

        driver = new FirefoxDriver();
        logger.info("The FireFox Bowser");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("The URl is opened successfulll");
        
    }

    @AfterMethod
    public void AfterMethod() {

        driver.quit();   
        logger.info("The firfox is close");
    }

    @Test(dataProvider = "valid", dataProviderClass = DataProvidersss.class)
    public void login_valid(String username, String pass) {

        driver.findElement(By.xpath("//a[@title = 'My Account']")).click();

        driver.findElement(By.xpath("//ul//a[text() = 'Login']")).click();

        WebElement username1 = driver.findElement(By.xpath("//input[@id = 'input-email']"));
        username1.sendKeys(username);

        WebElement pass1 = driver.findElement(By.xpath("//input[@id = 'input-password']"));
        pass1.sendKeys(pass);

        driver.findElement(By.xpath("//input[@value = 'Login']")).click();
        
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text() = 'My Account']")));
        String value = account.getText();
        
        try {
            Assert.assertEquals(value, "My Account");
            logger.info("Login successful");
            System.out.println("The Login is successful");
        } catch (AssertionError e) {
        		logger.warn("The user is not found");
            logger.error("Login failed - Expected: My Account but Found: " + value + e.getMessage());
            throw e;  
        }
    }
    
    
    @Test(dataProvider = "invalid", dataProviderClass = DataProvidersss.class)
    public void login_invalid(String username, String pass) throws Exception {

        driver.findElement(By.xpath("//a[@title = 'My Account']")).click();

        driver.findElement(By.xpath("//ul//a[text() = 'Login']")).click();

        WebElement username1 = driver.findElement(By.xpath("//input[@id = 'input-email']"));
        username1.sendKeys(username);

        WebElement pass1 = driver.findElement(By.xpath("//input[@id = 'input-password']"));
        pass1.sendKeys(pass);

        driver.findElement(By.xpath("//input[@value = 'Login']")).click();
        
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")));
        String letter = warning.getText();
        
        try {
        Assert.assertEquals(letter, "Warning: No match for E Address and/or Password.");
        System.out.println("The invalid login");
        logger.info("Invalid login");
        }
        catch(AssertionError e) {
        		logger.warn("The invalid user is accepted");
        		logger.error("The invalid user account is accpted" + e.getMessage());
        		throw e;
        		
        }
        
    }

    
    
}
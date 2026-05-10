package com.tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	 public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	 public static Logger logger = LogManager.getLogger(BaseTest.class);

	    public WebDriver getDriver() {
	        return driver.get();
	    }

	    @Parameters({"url"})
	    @BeforeMethod
	    public void setup(String url) {

	        WebDriver localDriver = new FirefoxDriver();
	        localDriver.manage().window().maximize();
	        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	        driver.set(localDriver);

	        getDriver().get(url);
	        logger.info("Browser opened in Thread: " + Thread.currentThread().getId());
	    }

	    @AfterMethod
	    public void teardown() {
	        getDriver().quit();
	        driver.remove();
	        logger.info("The browser closed");
	    }	
	

}

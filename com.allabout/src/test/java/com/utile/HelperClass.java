package com.utile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    public static HelperClass helperClass = new HelperClass();


    private HelperClass(){
        WebDriver driver1 = new FirefoxDriver();
        driver.set(driver1);

        wait.set(new WebDriverWait(driver1, Duration.ofSeconds(15)));
        driver1.manage().window().maximize();
    }

    // Initialize Driver
    public static void setUpDriver() {
        if (helperClass == null) {
            helperClass = new HelperClass();
        }
    }

    // Get Driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Get Wait
    public static WebDriverWait getWait() {
        return wait.get();
    }

    // Open URL
    public static void openPage(String url) {
        getDriver().get(url);
    }

    // Quit Driver
    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        helperClass = null;
    }
}

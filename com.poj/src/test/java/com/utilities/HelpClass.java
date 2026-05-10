package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelpClass {

    private static HelpClass helpClass;
    public final static int TIMEOUT = 10;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    // Private Constructor
    private HelpClass() {
        WebDriver driverInstance = new FirefoxDriver();
        driver.set(driverInstance);

        wait.set(new WebDriverWait(driverInstance, Duration.ofSeconds(TIMEOUT)));

        driverInstance.manage().window().maximize();
    }

    // Initialize Driver
    public static void setUpDriver() {
        if (helpClass == null) {
            helpClass = new HelpClass();
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
        helpClass = null;
    }
}
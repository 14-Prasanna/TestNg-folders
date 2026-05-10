package com.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setUpDriver() {

        WebDriver driver1 = new ChromeDriver();

        driver.set(driver1);

        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver1.manage().window().maximize();
    }
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void openPage(String url) {
        getDriver().get(url);
    }

    public static void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
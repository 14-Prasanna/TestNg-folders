package com.ninja;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks extends Base {

    @Before
    public void setup() {

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        System.out.println("Browser Launched");
    }

    @After
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println("Browser Closed");
        }
    }
}
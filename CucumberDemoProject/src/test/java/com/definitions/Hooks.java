package com.definitions;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.utilities.HelperClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {

        HelperClass.setUpDriver();

        log.info("Scenario Started: " + scenario.getName());
    }

    @After
    public void teardown(Scenario scenario) {

        if (scenario.isFailed()) {

            File screenshot = ((TakesScreenshot) HelperClass.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            try {

                File destinationfile = new File(
                        "screenshots/"
                                + scenario.getName().replaceAll(" ", "_")
                                + ".png");

                FileUtils.copyFile(screenshot, destinationfile);

                byte[] screenshotbytes =
                        ((TakesScreenshot) HelperClass.getDriver())
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshotbytes,
                        "image/png",
                        "Failure Screenshot");

                log.error("Scenario Failed: " + scenario.getName());

            } catch (IOException e) {

                log.error("Failed to take screenshot: " + e.getMessage());
            }

        } else {

            log.info("Scenario Passed: " + scenario.getName());
        }

        HelperClass.tearDown();
    }
}
package com.definitions;

import com.utilities.HelpClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Hooks {

    public static Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        HelpClass.setUpDriver();
        logger.info("Browser launched successfully");
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                File screenshot = ((TakesScreenshot) HelpClass.getDriver()).getScreenshotAs(OutputType.FILE);

                String screenshotName = scenario.getName().replaceAll(" ", "_") + ".png";
                File dest = new File("screenshots/" + screenshotName);

                FileUtils.copyFile(screenshot, dest);

                byte[] screenByte = ((TakesScreenshot) HelpClass.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenByte, "image/png", "Failure Screenshot");

                logger.error("Scenario FAILED: " + scenario.getName());

            } catch (Exception e) {
                logger.error("Screenshot capture failed: " + e.getMessage());
            }
        } else {
            logger.info("Scenario PASSED: " + scenario.getName());
        }

        HelpClass.tearDown();
        logger.info("Browser closed successfully");
    }
}
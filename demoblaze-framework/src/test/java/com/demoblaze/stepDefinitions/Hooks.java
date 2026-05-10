package com.demoblaze.stepDefinitions;

import com.demoblaze.utils.ConfigReader;
import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before(order = 1)
    public void setUp(Scenario scenario) {
        log.info("============================================================");
        log.info("  SCENARIO STARTED: {}", scenario.getName());
        log.info("  Tags: {}", scenario.getSourceTagNames());
        log.info("============================================================");

        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();

        DriverManager.initDriver(browser, headless);

        Allure.description("Scenario: " + scenario.getName());
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        log.info("============================================================");

        if (scenario.isFailed()) {
            log.error("  SCENARIO FAILED: {}", scenario.getName());

            if (DriverManager.getDriver() != null) {
                String screenshotPath = ScreenshotUtil.captureOnFailure(
                        DriverManager.getDriver(),
                        scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                );
                log.error("  Failure screenshot: {}", screenshotPath);

                byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        } else {
            log.info("  SCENARIO PASSED: {}", scenario.getName());

            if (Boolean.parseBoolean(ConfigReader.getProperty("screenshot.on.pass", "false"))) {
                if (DriverManager.getDriver() != null) {
                    ScreenshotUtil.captureOnPass(
                            DriverManager.getDriver(),
                            scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                    );
                }
            }
        }

        log.info("============================================================");
        DriverManager.quitDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.getDriver() != null) {
            log.warn("Step failed - capturing screenshot");
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Failure Screenshot");
        }
    }
}

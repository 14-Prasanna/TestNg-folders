package com.demoblaze.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = ConfigReader.getScreenshotPath();

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_DIR + fileName;

        try {

            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            log.info("Screenshot captured: {}", filePath);

            attachScreenshotToAllure(driver, testName);

            return filePath;

        } catch (IOException e) {
            log.error("Failed to capture screenshot for '{}': {}", testName, e.getMessage());
            return null;
        }
    }

    public static void attachScreenshotToAllure(WebDriver driver, String attachmentName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    attachmentName + " - Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png"
            );
            log.info("Screenshot attached to Allure report: {}", attachmentName);
        } catch (Exception e) {
            log.error("Failed to attach screenshot to Allure: {}", e.getMessage());
        }
    }

    public static String captureOnFailure(WebDriver driver, String testName) {
        log.warn("Capturing failure screenshot for: {}", testName);
        String path = captureScreenshot(driver, "FAIL_" + testName);
        log.warn("Failure screenshot saved: {}", path);
        return path;
    }

    public static String captureOnPass(WebDriver driver, String testName) {
        log.info("Capturing pass screenshot for: {}", testName);
        return captureScreenshot(driver, "PASS_" + testName);
    }
}

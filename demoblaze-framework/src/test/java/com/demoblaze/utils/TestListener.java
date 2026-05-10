package com.demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("====================================================");
        log.info("  TEST SUITE STARTED: {}", context.getName());
        log.info("====================================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("====================================================");
        log.info("  TEST SUITE FINISHED: {}", context.getName());
        log.info("  PASSED  : {}", context.getPassedTests().size());
        log.info("  FAILED  : {}", context.getFailedTests().size());
        log.info("  SKIPPED : {}", context.getSkippedTests().size());
        log.info("====================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("--------------------------------------------------");
        log.info("  TEST STARTED : {}", result.getMethod().getMethodName());
        log.info("  Class        : {}", result.getTestClass().getName());
        log.info("--------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("[PASS] Test: {} | Duration: {}ms",
                result.getMethod().getMethodName(),
                result.getEndMillis() - result.getStartMillis());

        if (Boolean.parseBoolean(ConfigReader.getProperty("screenshot.on.pass", "false"))) {
            if (DriverManager.getDriver() != null) {
                ScreenshotUtil.captureOnPass(DriverManager.getDriver(),
                        result.getMethod().getMethodName());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("[FAIL] Test: {} | Reason: {}",
                result.getMethod().getMethodName(),
                result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown");

        if (DriverManager.getDriver() != null) {
            String screenshotPath = ScreenshotUtil.captureOnFailure(DriverManager.getDriver(),
                    result.getMethod().getMethodName());
            log.error("  Screenshot saved: {}", screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("[SKIP] Test: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("[PARTIAL] Test: {} - within success percentage",
                result.getMethod().getMethodName());
    }
}

package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("START: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("FAIL: " + result.getName());

        try {
            Object testClass = result.getInstance();

            WebDriver driver = (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);

            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            log.error("Screenshot saved at: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIPPED: " + result.getName());
    }
}
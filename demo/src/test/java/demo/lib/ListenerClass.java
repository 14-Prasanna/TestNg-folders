package demo.lib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import demo.tests.BaseTest;

public class ListenerClass extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        System.out.println("The Test is failed: " + testName);

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            File destFile = new File("D:\\TestNG\\demo\\src\\test\\resources\\screenshots\\"
                    + testName + ".png");

            FileUtils.copyFile(srcFile, destFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }
}
package com.report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestReport {
	
	public static void main(String args[]) {
		
		ExtentHtmlReporter report = new ExtentHtmlReporter("extentReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		
		WebDriver driver = new FirefoxDriver();
		ExtentTest test = extent.createTest("Google Search Test ");
		
		try {
			driver.get("https://www.google.co");
			test.pass("Navigated to Google");
		}catch(Exception e) {
			test.fail("Test failed due to exception" + e.getMessage());
		}finally {
			driver.quit();
			extent.flush();
		}
	}

}

package com.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/Login.feature", glue = "com.definitions", plugin = { "pretty",
		"html:target/cucumber-reports.html", "json:target/cucmber.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true)

public class TestNGRunner extends AbstractTestNGCucumberTests {
	@Override
	  @DataProvider(parallel = true)
	  public Object[][] scenarios() {
	        return super.scenarios();
	    }
}

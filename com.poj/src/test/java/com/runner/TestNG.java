package com.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


// For ExtentReport
//@CucumberOptions(
//        features = "src/test/resources/features/",
//        glue = "com.definitions",
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports.html",
//                "json:target/cucumber.json",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//        },
//        monochrome = true
//)


// For Allure

@Test
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com.definitions",
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}

)

public class TestNG extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-report/Cucumber.html" },
        features = "src/test/resources/com/features/",
        glue = { "in.stepDefinition", "Hooks" }
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
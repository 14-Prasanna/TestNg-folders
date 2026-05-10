package com.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty ","html:target/cucumber-report/Cucumber1.html"},
features = "src/com/resources/com/features/Login.feature",
tags = "@Valid", glue = "in.stepDefinition.LaunchAndLoginDefinition")

public class JUnitRunner {
}

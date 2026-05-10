package com.demoblaze.runner;

import com.demoblaze.utils.ExcelDataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = {"com.demoblaze.stepDefinitions"},
        tags = "@login and @negative or @login and @invalid_login",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/login-invalid-report.html",
                "json:target/cucumber-reports/login-invalid.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class LoginInvalidRunner extends AbstractTestNGCucumberTests {

    private static final Logger log = LogManager.getLogger(LoginInvalidRunner.class);

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        log.info("Loading invalid login scenarios from Excel...");
        return super.scenarios();
    }

    @DataProvider(name = "invalidLoginData", parallel = true)
    public Object[][] getInvalidLoginData() {
        log.info("DataProvider: Loading InvalidCredentials sheet");
        Object[][] data = ExcelDataProvider.getInvalidLoginData();
        log.info("DataProvider: {} rows loaded from InvalidCredentials", data.length);
        return data;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("====================================================");
        log.info("  LOGIN RUNNER (INVALID) - SUITE STARTED");
        log.info("====================================================");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("====================================================");
        log.info("  LOGIN RUNNER (INVALID) - SUITE COMPLETED");
        log.info("====================================================");
    }

    @Test(dataProvider = "invalidLoginData",
          description = "Data-driven login test with INVALID credentials from Excel",
          groups = {"negative", "regression"})
    public void dataProviderInvalidLoginTest(String username, String password, String expectedError) {
        log.info("----------------------------------------------------");
        log.info("  [Invalid DataProvider] username='{}' | expectedError='{}'", username, expectedError);
        log.info("----------------------------------------------------");
        log.info("Excel row: username='{}', password='{}', expectedError='{}'",
                username, password, expectedError);
    }
}

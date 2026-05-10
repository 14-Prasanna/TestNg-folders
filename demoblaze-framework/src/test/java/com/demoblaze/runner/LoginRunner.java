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
        tags = "@login and @valid_login or @login and @smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/login-valid-report.html",
                "json:target/cucumber-reports/login-valid.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class LoginRunner extends AbstractTestNGCucumberTests {

    private static final Logger log = LogManager.getLogger(LoginRunner.class);

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        log.info("Loading valid login scenarios from Excel...");
        return super.scenarios();
    }

    @DataProvider(name = "validLoginData", parallel = true)
    public Object[][] getValidLoginData() {
        log.info("DataProvider: Loading ValidCredentials sheet");
        Object[][] data = ExcelDataProvider.getValidLoginData();
        log.info("DataProvider: {} rows loaded from ValidCredentials", data.length);
        return data;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("====================================================");
        log.info("  LOGIN RUNNER (VALID) - SUITE STARTED");
        log.info("====================================================");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("====================================================");
        log.info("  LOGIN RUNNER (VALID) - SUITE COMPLETED");
        log.info("====================================================");
    }

    @Test(dataProvider = "validLoginData",
          description = "Data-driven login test with valid credentials from Excel",
          groups = {"regression", "smoke"})
    @Parameters({"browser", "baseUrl"})
    public void dataProviderValidLoginTest(String username, String password, String expectedResult) {
        log.info("----------------------------------------------------");
        log.info("  [DataProvider Test] username='{}' | expected='{}'", username, expectedResult);
        log.info("----------------------------------------------------");

        log.info("Excel row: username='{}', password='{}', expectedResult='{}'",
                username, password, expectedResult);
    }
}

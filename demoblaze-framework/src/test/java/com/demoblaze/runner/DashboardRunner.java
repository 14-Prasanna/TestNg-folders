package com.demoblaze.runner;

import com.demoblaze.utils.ExcelDataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features/Dashboard.feature",
        glue = {"com.demoblaze.stepDefinitions"},
        tags = "@dashboard",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/dashboard-report.html",
                "json:target/cucumber-reports/dashboard.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class DashboardRunner extends AbstractTestNGCucumberTests {

    private static final Logger log = LogManager.getLogger(DashboardRunner.class);

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        log.info("Loading dashboard scenarios...");
        return super.scenarios();
    }

    @DataProvider(name = "dashboardData", parallel = true)
    public Object[][] getDashboardData() {
        log.info("DataProvider: Loading ValidCredentials for dashboard tests");
        return ExcelDataProvider.getValidLoginData();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("====================================================");
        log.info("  DASHBOARD RUNNER - SUITE STARTED");
        log.info("====================================================");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("====================================================");
        log.info("  DASHBOARD RUNNER - SUITE COMPLETED");
        log.info("====================================================");
    }

    @Test(dataProvider = "dashboardData",
          description = "Data-driven dashboard test from Excel",
          groups = {"regression", "dashboard"})
    public void dataProviderDashboardTest(String username, String password, String expectedResult) {
        log.info("----------------------------------------------------");
        log.info("  [Dashboard DataProvider] username='{}' | expected='{}'", username, expectedResult);
        log.info("----------------------------------------------------");
    }
}

package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-report/Cucumber.html" },
        features = "src/test/resources/",
        glue = { "in.stepDif" }
)
public class TestNg extends AbstractTestNGCucumberTests {

}
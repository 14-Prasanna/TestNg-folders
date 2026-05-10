package in.stepDif;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginWithMD {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User launch the fireFox Browser")
    public void userLaunchTheFireFoxBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @And("User is lanuch the DemoBalze Phone Page")
    public void userIsLanuchTheDemoBalzePhonePage() {
        driver.get("https://demoblaze.com/");

    }

    @When("the user click the Login click")
    public void theUserClickTheLoginClick() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
    }

    @Then("User enters invalid credentials and login will be unsuccessful with error message")
    public void userEntersInvalidCredentialsAndLoginWillBeUnsuccessfulWithErrorMessage(DataTable dataTable) {
        List<Map<String, String>> l1 = dataTable.asMaps(String.class, String.class);

        for(Map<String,String>form : l1){
            String username = form.get("username");
            System.out.println("Username: " + username);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);

            String pass = form.get("password");
            System.out.println("password: " + pass );
            driver.findElement(By.id("loginpassword")).sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();


            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualMsg = alert.getText();
            alert.accept();

            String expectedMsg = form.get("message");
            System.out.println("ExpectedMsg: " + expectedMsg);
            Assert.assertEquals(actualMsg, expectedMsg);
            System.out.println("Alert verified successfully: " + actualMsg);
        }
    }
}

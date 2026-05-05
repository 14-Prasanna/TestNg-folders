package in.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LaunchAndLoginDefinition {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void before() {
        System.out.println("--------------------------------------------");
        System.out.println("Firefox Browser Launching...");
        System.out.println("--------------------------------------------");
    }

    @Given("the user launch the firefox browser")
    public void the_user_launch_the_firefox_browser() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @When("the user enters the URL as {string} in the browser")
    public void the_user_enters_the_url_as_in_the_browser(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Then("the page title should be verified")
    public void the_page_title_should_be_verified() {
        Assert.assertEquals(driver.getTitle(), "STORE");
    }

    @When("the user clicks on the Login link")
    public void the_user_clicks_on_the_login_link() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
    }

    @Then("the Login card title should be verified")
    public void the_login_card_title_should_be_verified() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));
        WebElement titleLogin = driver.findElement(By.id("logInModalLabel"));
        Assert.assertEquals(titleLogin.getText().trim(), "Log in");
    }

    @When("the user enters username as {string} and password as {string}")
    public void the_user_enters_username_as_and_password_as(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();
    }

    @Then("the user should be login successfully and {string} should be display")
    public void the_user_should_be_login_successfully_and_should_be_display(String expectedWelcome) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text() = 'Welcome admin']")));

        String actualWelcome = driver.findElement(By.xpath("//a[text() = 'Welcome admin']")).getText();
        Assert.assertEquals(actualWelcome, expectedWelcome);
    }

    @Then("the Logout should be displayed instead of Login")
    public void the_logout_should_be_displayed_instead_of_login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        String logoutText = driver.findElement(By.id("logout2")).getText().trim();
        Assert.assertEquals(logoutText, "Log out");
    }

    @Then("the alert message {string} should be display")
    public void the_alert_message_should_be_display(String expectedAlertMessage) {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlertMessage = driver.switchTo().alert().getText().trim();
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);
        driver.switchTo().alert().accept();
    }

    @After
    public void after() {
        System.out.println("--------------------------------------------");
        System.out.println("Browser Closing...");
        System.out.println("--------------------------------------------");

        if (driver != null) {
            driver.quit();
        }
    }
}
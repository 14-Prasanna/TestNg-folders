package com.ninja;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login extends Base {

    WebDriverWait wait;

    @Given("User launch the browser")
    public void user_launch_the_browser() {

        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @And("User navigates to the Login page")
    public void user_navigates_to_the_login_page() {

        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String email, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);

        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
    }

    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {

        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Then("User should login successfully")
    public void user_should_login_successfully() {

        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='My Account']"))).getText();

        Assert.assertEquals(actualTitle, "My Account");

        System.out.println("Login Successful");

        driver.quit();
    }
}
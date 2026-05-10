package com.ninja;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Register extends Base {

    WebDriverWait wait;

    @And("User navigates to the Register Account page")
    public void user_navigates_to_the_register_account_page() {

        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @When("User enters register details and submits the form")
    public void user_enters_register_details_and_submits_the_form(DataTable dataTable) {

        List<Map<String, String>> users =
                dataTable.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("input-firstname")));

            driver.findElement(By.id("input-firstname")).clear();
            driver.findElement(By.id("input-firstname"))
                    .sendKeys(user.get("firstname"));

            driver.findElement(By.id("input-lastname")).clear();
            driver.findElement(By.id("input-lastname"))
                    .sendKeys(user.get("lastname"));

            driver.findElement(By.id("input-email")).clear();
            driver.findElement(By.id("input-email"))
                    .sendKeys(user.get("email"));

            driver.findElement(By.id("input-telephone")).clear();
            driver.findElement(By.id("input-telephone"))
                    .sendKeys(user.get("telephone"));

            driver.findElement(By.id("input-password")).clear();
            driver.findElement(By.id("input-password"))
                    .sendKeys(user.get("password"));

            driver.findElement(By.id("input-confirm")).clear();
            driver.findElement(By.id("input-confirm"))
                    .sendKeys(user.get("confirmpassword"));

            driver.findElement(
                            By.xpath("//input[@name='newsletter' and @value='0']"))
                    .click();

            driver.findElement(By.name("agree")).click();

            driver.findElement(
                            By.xpath("//input[@value='Continue']"))
                    .click();
        }
    }

    @Then("User account should be created successfully")
    public void user_account_should_be_created_successfully() {

        try {

            String actualText =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//h1[text()='Your Account Has Been Created!']")))
                            .getText();

            Assert.assertEquals(actualText,
                    "Your Account Has Been Created!");

            System.out.println("Account Created Successfully");

        } catch (Exception e) {

            System.out.println("Account NOT Created");

            String pageSource = driver.getPageSource();

            if (pageSource.contains("Warning: E-Mail Address is already registered!")) {

                System.out.println("Email already exists");
            }

            else if (pageSource.contains("Password confirmation does not match password!")) {

                System.out.println("Password mismatch");
            }

            else if (pageSource.contains("Privacy Policy")) {

                System.out.println("Privacy Policy checkbox not selected");
            }

            else {

                System.out.println("Unknown validation issue");
            }

            throw e;
        }
    }
}
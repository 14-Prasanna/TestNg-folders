package in.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Hooks.hooks;
import io.cucumber.java.en.*;

public class Purchase_steps {

    @Given("User clicks on Place Order button")
    public void user_clicks_on_place_order_button() {

        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();
    }

    @When("User enters purchase details")
    public void user_enters_purchase_details() {

        WebElement name = hooks.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        name.sendKeys("Prasanna Venkatesh K");

        hooks.driver.findElement(By.id("country")).sendKeys("India");
        hooks.driver.findElement(By.id("city")).sendKeys("Trichy");
        hooks.driver.findElement(By.id("card")).sendKeys("1234567890123456");
        hooks.driver.findElement(By.id("month")).sendKeys("05");
        hooks.driver.findElement(By.id("year")).sendKeys("2026");
    }

    @When("User clicks on Purchase button")
    public void user_clicks_on_purchase_button() {

        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']"))).click();
    }

    @Then("User should see purchase confirmation")
    public void user_should_see_purchase_confirmation() {

        WebElement confirmation = hooks.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));

        System.out.println("Order Placed Successfully");

        Assert.assertTrue(confirmation.isDisplayed());
        hooks.driver.findElement(By.xpath("//button[text()='OK']")).click();
    }

    @Then("User should see alert for empty purchase details")
    public void user_should_see_alert_for_empty_purchase_details() {

        System.out.println("Purchase attempted with empty details");
    }

    @When("User enters only name and card details")
    public void user_enters_only_name_and_card_details() {

        hooks.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Mythily");

        hooks.driver.findElement(By.id("card")).sendKeys("1234567890123456");
    }
}
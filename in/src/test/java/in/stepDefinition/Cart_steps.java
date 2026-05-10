package in.stepDefinition;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Hooks.hooks;
import io.cucumber.java.en.*;

public class Cart_steps {

    @Given("User navigates to {string}")
    public void user_navigates_to(String url) {
        hooks.driver.get(url);
    }

    @When("User selects product {string}")
    public void user_selects_product(String productName) {
        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText(productName))).click();
    }

    @When("User clicks on Add to cart")
    public void user_clicks_on_add_to_cart() {
        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
    }

    @Then("User should see product added alert")
    public void user_should_see_product_added_alert() {

        Alert alert = hooks.wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();

        hooks.driver.navigate().back();
    }

    @Given("User adds product {string} to cart")
    public void user_adds_product_to_cart(String productName) {

        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText(productName))).click();

        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();

        Alert alert = hooks.wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        hooks.driver.navigate().back();
    }

    @When("User clicks on Cart button")
    public void user_clicks_on_cart_button() {
        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();
    }

    @Then("User should see the product in cart")
    public void user_should_see_the_product_in_cart() {

        String page = hooks.driver.getPageSource();

        if (page.contains("Nokia lumia 1520")) {
            System.out.println("Product is present in cart");
        } else {
            System.out.println("Product not found");
        }
    }

    @When("User deletes the product")
    public void user_deletes_the_product() {
        hooks.wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Delete"))).click();
    }

    @Then("Cart should be empty")
    public void cart_should_be_empty() {

        String page = hooks.driver.getPageSource();

        if (!page.contains("Sony vaio i5")) {
            System.out.println("Cart is empty");
        }
    }
}
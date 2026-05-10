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
import java.util.List;

public class CategoriesDefinition {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void beforeMethod() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("the user is on the DemoBlaze Home Page")
    public void the_user_is_on_the_demo_blaze_home_page() {
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    @When("the user clicks on the {string} category")
    public void the_user_clicks_on_the_category(String category) {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='" + category + "']")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    @Then("only {string} related products should be displayed")
    public void only_related_products_should_be_displayed(String category) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));

        List<WebElement> products = driver.findElements(By.xpath("//div[@id='tbodyid']//h4/a"));

        System.out.println("---------------------------------------");
        System.out.println("Category Selected: " + category);
        System.out.println("Products Displayed:");
        System.out.println("---------------------------------------");

        for (WebElement product : products) {
            System.out.println(product.getText());
        }

        Assert.assertFalse(products.isEmpty(), "No products displayed for category: " + category);
    }

    @And("the user clicks on the product {string}")
    public void the_user_clicks_on_the_product(String productName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='" + productName + "']")
        )).click();
    }

    @Then("{string} rate should be verified")
    public void rate_should_be_verified(String productName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='" + productName + "']")));

        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[@class='price-container']")
        ));

        String priceText = priceElement.getText();

        System.out.println("---------------------------------------");
        System.out.println("Product Selected: " + productName);
        System.out.println("Price Displayed: " + priceText);
        System.out.println("---------------------------------------");

        Assert.assertTrue(priceText.contains("$"), "Price not displayed properly for product: " + productName);
    }

    @After
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
}
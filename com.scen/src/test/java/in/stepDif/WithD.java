//package in.stepDif;
//
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.jspecify.annotations.NonNull;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//import java.awt.*;
//import java.lang.reflect.Type;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WithD {
//
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    @Given("User launch the fireFox Browser")
//    public void userLaunchTheFireFoxBrowser() {
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @And("User is launch the  Phone Page")
//    public void userIsLanuchThePhonePage(DataTable dataTable) throws InterruptedException {
//        List<List<String>> l1 = dataTable.asLists(String.class);
//        String url1 = l1.get(0).get(1);
//        driver.get(url1);
//    }
//
//    @When("the user click the Login click")
//    public void the_user_click_the_login_click() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
//    }
//
//    @And("User enters username and password")
//    public void userEntersTheAsAndPasswordAs(DataTable dataTable) {
//        List<List<String>> l1 = dataTable.asLists(String.class);
//        String username = l1.get(0).get(0);
//        String pass = l1.get(0).get(1);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);
//        driver.findElement(By.id("loginpassword")).sendKeys(pass);
//
//    }
//
//    @When("User clicks Login Button")
//    public void user_clicks_login_button() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();
//    }
//
//    @Then("User should able to see an Message as {string}")
//    public void user_should_able_to_see_an_message_as(String expectedMsg) {
//
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        String actualMsg = alert.getText();
//        alert.accept();
//
//        Assert.assertEquals(actualMsg, expectedMsg);
//        System.out.println("Alert verified successfully: " + actualMsg);
//    }
//}

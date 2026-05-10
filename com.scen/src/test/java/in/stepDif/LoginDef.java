//package in.stepDif;
//
//import io.cucumber.java.After;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.messages.types.Hook;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//import java.time.Duration;
//
//
//
//public class LoginDef {
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
//    @And("User is lanuch the DemoBalze Phone Page")
//    public void user_is_on_the_demo_balze_phone_page() {
//        driver.get("https://demoblaze.com/");
//    }
//
//    @When("the user click the Login click")
//    public void the_user_click_the_login_click() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
//    }
//
//    @When("User enters the as {string} and password as {string}")
//    public void user_enters_the_as_and_password_as(String username, String password) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);
//        driver.findElement(By.id("loginpassword")).sendKeys(password);
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
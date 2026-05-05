package demo.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(css = "#login2")
    public WebElement click_login;

    @FindBy(css = "#loginusername")
    public WebElement username;

    @FindBy(css = "#loginpassword")
    public WebElement password;

    @FindBy(css = "button[onclick='logIn()']")
    public WebElement click_button;

    @FindBy(css = "#logout2")
    public WebElement logout;

    public String LoginValid(String user, String pass) {

        click_login.click();

        WebElement a = wait.until(ExpectedConditions.visibilityOf(username));
        a.clear();
        a.sendKeys(user);

        password.clear();
        password.sendKeys(pass);

        click_button.click();

        WebElement log = wait.until(ExpectedConditions.visibilityOf(logout));
        return log.getCssValue("display");
    }

    public String LoginInvalid(String user, String pass) {

        click_login.click();

        WebElement a = wait.until(ExpectedConditions.visibilityOf(username));
        a.sendKeys(user);

        password.sendKeys(pass);
        click_button.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        String alertMsg = alert.getText();
        alert.accept();

        return alertMsg;
    }
}
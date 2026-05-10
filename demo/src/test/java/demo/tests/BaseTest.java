package demo.tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Logger logger = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"url"})
    @BeforeMethod
    public void beferMethod(@Optional("https://demoblaze.com") String url) {

       
        WebDriver driver1 = new FirefoxDriver();

        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.set(driver1);
        getDriver().get(url);
    }

    @AfterMethod
    public void AfterMethod() {
        getDriver().quit();
        driver.remove();
    }
}
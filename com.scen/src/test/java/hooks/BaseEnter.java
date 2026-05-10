package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseEnter {

    public static WebDriver driver;
    public static WebDriverWait wait;


    @Before
    public void before(){

        System.out.println("-------Before executing-------");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");

        driver = new FirefoxDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @After
    public void tearDown() {
        driver.quit();
        System.out.println("-------After Execution--------");
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }
}

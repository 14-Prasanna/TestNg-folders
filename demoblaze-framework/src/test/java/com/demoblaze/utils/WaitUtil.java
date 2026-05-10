package com.demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private static final Logger log = LogManager.getLogger(WaitUtil.class);
    private static final int DEFAULT_TIMEOUT = ConfigReader.getExplicitWait();

    private WaitUtil() {}

    public static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        log.debug("Waiting for element to be visible");
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        log.debug("Waiting for locator to be visible: {}", locator);
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
        log.debug("Waiting for element to be clickable");
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(WebDriver driver, By locator) {
        log.debug("Waiting for locator to be clickable: {}", locator);
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForAlert(WebDriver driver) {
        log.debug("Waiting for alert to appear");
        getWait(driver).until(ExpectedConditions.alertIsPresent());
    }

    public static boolean waitForUrlContains(WebDriver driver, String urlFragment) {
        log.debug("Waiting for URL to contain: {}", urlFragment);
        return getWait(driver).until(ExpectedConditions.urlContains(urlFragment));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        log.debug("Waiting for element to become invisible: {}", locator);
        return getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForTextPresent(WebDriver driver, WebElement element, String text) {
        log.debug("Waiting for text '{}' in element", text);
        return getWait(driver).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void shortPause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package com.demoblaze.pages;

import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final Logger log = LogManager.getLogger(getClass());
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        WaitUtil.waitForClickability(driver, element).click();
    }

    protected void click(By locator) {
        WaitUtil.waitForClickability(driver, locator).click();
    }

    protected void type(WebElement element, String text) {
        WaitUtil.waitForVisibility(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return WaitUtil.waitForVisibility(driver, element).getText().trim();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void navigateTo(String url) {
        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected String getAlertText() {
        WaitUtil.waitForAlert(driver);
        return driver.switchTo().alert().getText();
    }

    protected void acceptAlert() {
        WaitUtil.waitForAlert(driver);
        driver.switchTo().alert().accept();
        log.info("Alert accepted");
    }

    protected void dismissAlert() {
        WaitUtil.waitForAlert(driver);
        driver.switchTo().alert().dismiss();
    }
}

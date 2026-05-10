package com.pom.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    By dashboardText = By.xpath("//h6[text()='Dashboard']");
    By loader = By.xpath("//div[contains(@class,'oxd-loading-spinner')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public String Homepage() {

        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardText)).getText();
    }
}
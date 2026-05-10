package com.actions;

import com.pages.HomePageLocators;
import com.utilities.HelpClass;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageAction {

    WebDriverWait wait;

    HomePageLocators homePageLocators;

    public HomePageAction(){

        this.homePageLocators = new HomePageLocators();

        PageFactory.initElements(HelpClass.getDriver(), homePageLocators);

        wait = new WebDriverWait(HelpClass.getDriver(), Duration.ofSeconds(20));
    }

    public String getHomePage(){

        wait.until(
                ExpectedConditions.visibilityOf(homePageLocators.homepage)
        );

        return homePageLocators.homepage.getText();
    }
}
package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_product")
    WebElement searchBox;

    @FindBy(id = "submit_search")
    WebElement searchBtn;

    @FindBy(xpath = "(//div[@class='productinfo text-center']/p)[1]")
    WebElement firstProduct;

    public void searchProduct(String keyword) {
        searchBox.sendKeys(keyword);
        searchBtn.click();
    }

    public String getFirstProductName() {
        return firstProduct.getText();
    }
}
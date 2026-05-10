package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    WebElement checkoutBtn;

    public void verifyCart() {
        checkoutBtn.isDisplayed();
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }
}
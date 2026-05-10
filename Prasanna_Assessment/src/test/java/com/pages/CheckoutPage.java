package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "message")
    WebElement messageBox;

    @FindBy(xpath = "//a[text()='Place Order']")
    WebElement placeOrderBtn;

    @FindBy(name = "name_on_card")
    WebElement name;

    @FindBy(name = "card_number")
    WebElement card;

    @FindBy(name = "cvc")
    WebElement cvc;

    @FindBy(name = "expiry_month")
    WebElement month;

    @FindBy(name = "expiry_year")
    WebElement year;

    @FindBy(id = "submit")
    WebElement payBtn;

    @FindBy(xpath = "//b[text()='Order Placed!']")
    WebElement orderMsg;

    public void placeOrder() {

        messageBox.sendKeys("Test Order");
        placeOrderBtn.click();

        name.sendKeys("Mythily");
        card.sendKeys("123456789");
        cvc.sendKeys("123");
        month.sendKeys("12");
        year.sendKeys("2026");

        payBtn.click();
    }

    public String getOrderMessage() {
        return orderMsg.getText();
    }
}
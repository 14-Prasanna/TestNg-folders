package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends BasePage {

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//a[text()='Add to cart'])[1]")
    WebElement addToWishlist;

    @FindBy(xpath = "//u[text()='View Cart']")
    WebElement viewCart;

    @FindBy(xpath = "//td[@class='cart_description']/h4/a")
    WebElement productName;

    public void addToWishlist() {
        addToWishlist.click();
        viewCart.click();
    }

    public String getWishlistProductName() {
        return productName.getText();
    }

    public void moveToCart() {
    }
}
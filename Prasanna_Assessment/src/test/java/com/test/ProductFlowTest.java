package com.test;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.SearchPage;
import com.pages.WishlistPage;
import com.pages.CartPage;
import com.pages.CheckoutPage;

public class ProductFlowTest {

    WebDriver driver;

    SearchPage search;
    WishlistPage wishlist;
    CartPage cart;
    CheckoutPage checkout;

    private static final Logger log = LogManager.getLogger(ProductFlowTest.class);

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        search = new SearchPage(driver);
        wishlist = new WishlistPage(driver);
        cart = new CartPage(driver);
        checkout = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void searchProduct() {

        String keyword = "dress";
        search.searchProduct(keyword);

        String productName = search.getFirstProductName();
        Assert.assertTrue(productName.toLowerCase().contains(keyword));
        log.info("Search validated");
    }

    @Test(priority = 2, dependsOnMethods = "searchProduct")
    public void addToWishlist() {

        wishlist.addToWishlist();

        String name = wishlist.getWishlistProductName();
        Assert.assertNotNull(name);
        log.info("Wishlist validated");
    }

    @Test(priority = 3, dependsOnMethods = "addToWishlist")
    public void checkoutFlow() {

        SoftAssert soft = new SoftAssert();

        wishlist.moveToCart();
        cart.verifyCart();
        cart.clickCheckout();
        checkout.placeOrder();

        String actual = checkout.getOrderMessage();

        soft.assertEquals(actual, "WRONG MESSAGE");
        log.info("Checkout completed");
        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
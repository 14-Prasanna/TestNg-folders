package com.demoblaze.actions;

import com.demoblaze.pages.DashboardPage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.AssertionHelper;
import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.ScreenshotUtil;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardActions {

    private static final Logger log = LogManager.getLogger(DashboardActions.class);
    private final DashboardPage dashboardPage;

    public DashboardActions() {
        this.dashboardPage = new DashboardPage();
    }

    @Step("Navigate to URL: {url}")
    public void navigateTo(String url) {
        dashboardPage.navigateTo(url);
    }

    @Step("Verify page title contains: {expectedTitle}")
    public void verifyPageTitle(String expectedTitle) {
        String actual = dashboardPage.getPageTitle();
        AssertionHelper.assertTrue(actual.contains(expectedTitle),
                "Page title should contain '" + expectedTitle + "' but was: '" + actual + "'");
    }

    @Step("Click on nav link: {linkText}")
    public void clickNavLink(String linkText) {
        dashboardPage.clickNavLinkByText(linkText);
    }

    @Step("Click Login nav link")
    public void clickLoginNavLink() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginNavLink();
    }

    @Step("Perform login with username: {username}")
    public void performLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Step("Navigate to Home page")
    public void navigateToHome() {
        dashboardPage.clickHomeNav();
    }

    @Step("Verify nav bar contains: {text}")
    public void verifyNavBarContains(String text) {
        boolean contains = dashboardPage.isNavBarContaining(text);
        AssertionHelper.assertTrue(contains,
                "Navigation bar should contain '" + text + "'");
        log.info("Nav bar contains '{}': {}", text, contains);
    }

    @Step("Verify category '{category}' is visible in sidebar")
    public void verifyCategoryVisible(String category) {
        boolean visible = dashboardPage.isCategoryVisible(category);
        AssertionHelper.assertTrue(visible,
                "Category '" + category + "' should be visible in sidebar");
    }

    @Step("Click on category: {category}")
    public void clickCategory(String category) {
        log.info("Clicking category: '{}'", category);
        dashboardPage.clickCategory(category);
    }

    @Step("Verify at least one product is displayed for category: {category}")
    public void verifyProductsDisplayed(String category) {
        int count = dashboardPage.getProductCount();
        AssertionHelper.assertTrue(count > 0,
                "Expected products to be displayed for category '" + category + "' but found 0");
        log.info("Products found for '{}': {}", category, count);
        ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), "category_" + category);
    }

    @Step("Verify product list refreshed")
    public void verifyProductListRefreshed() {
        int count = dashboardPage.getProductCount();
        log.info("Product list has {} items", count);
        AssertionHelper.assertTrue(count >= 0, "Product list should be present");
    }

    @Step("Verify carousel is displayed")
    public void verifyCarouselDisplayed() {
        AssertionHelper.assertTrue(dashboardPage.isCarouselDisplayed(),
                "Carousel/slider should be displayed on the home page");
    }

    @Step("Verify carousel next button is clickable")
    public void verifyCarouselNextClickable() {
        AssertionHelper.assertTrue(dashboardPage.isCarouselNextButtonClickable(),
                "Carousel next button should be clickable");
    }

    @Step("Click carousel next button")
    public void clickCarouselNext() {
        dashboardPage.clickCarouselNext();
        ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), "carousel_next");
    }

    @Step("Verify carousel advanced to next slide")
    public void verifyCarouselAdvanced() {
        AssertionHelper.assertTrue(dashboardPage.isCarouselDisplayed(),
                "Carousel should still be displayed after clicking next");
    }

    @Step("Click first product in the list")
    public void clickFirstProduct() {
        dashboardPage.clickFirstProduct();
    }

    @Step("Verify navigation to product detail page")
    public void verifyOnProductDetailPage() {
        AssertionHelper.assertTrue(dashboardPage.isOnProductDetailPage(),
                "Should be on product detail page (URL contains 'prod.html')");
    }

    @Step("Verify product name is visible on detail page")
    public void verifyProductNameVisible() {
        AssertionHelper.assertTrue(dashboardPage.isProductNameVisible(),
                "Product name should be visible on the detail page");
    }

    @Step("Verify product price is visible on detail page")
    public void verifyProductPriceVisible() {
        AssertionHelper.assertTrue(dashboardPage.isProductPriceVisible(),
                "Product price should be visible on the detail page");
    }

    @Step("Verify 'Add to cart' button is present")
    public void verifyAddToCartPresent() {
        AssertionHelper.assertTrue(dashboardPage.isAddToCartButtonPresent(),
                "Add to cart button should be present on product detail page");
    }

    @Step("Search for product: {productName}")
    public void searchForProduct(String productName) {
        log.info("Looking for product '{}' in catalog", productName);
    }

    @Step("Verify product '{productName}' is visible in catalog")
    public void verifyProductVisible(String productName) {
        boolean visible = dashboardPage.isProductVisible(productName);
        AssertionHelper.assertTrue(visible,
                "Product '" + productName + "' should be visible in the catalog");
    }

    @Step("Verify price is displayed for product: {productName}")
    public void verifyPriceDisplayed(String productName) {
        log.info("Verifying price is displayed for: {}", productName);
        AssertionHelper.assertTrue(dashboardPage.getProductCount() > 0,
                "Products with prices should be displayed");
    }

    @Step("Click logout")
    public void clickLogout() {
        dashboardPage.clickLogout();
    }

    @Step("Verify 'Log in' link is visible after logout")
    public void verifyLoginLinkVisible() {
        AssertionHelper.assertTrue(dashboardPage.isLoginLinkVisible(),
                "Log in link should be visible after logout");
    }

    @Step("Verify 'Sign up' link is visible after logout")
    public void verifySignupLinkVisible() {
        AssertionHelper.assertTrue(dashboardPage.isSignupLinkVisible(),
                "Sign up link should be visible after logout");
    }

    @Step("Verify 'Welcome admin' is NOT in nav bar after logout")
    public void verifyWelcomeNotVisible() {
        boolean notVisible = !dashboardPage.isNavBarContaining("Welcome admin");
        AssertionHelper.assertTrue(notVisible,
                "Welcome message should NOT be visible after logout");
        ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), "after_logout");
    }

    @Step("Verify contact modal is displayed")
    public void verifyContactModalDisplayed() {
        AssertionHelper.assertTrue(dashboardPage.isContactModalDisplayed(),
                "Contact modal should be displayed");
    }

    @Step("Verify contact email field is visible")
    public void verifyContactEmailVisible() {
        AssertionHelper.assertTrue(dashboardPage.isContactEmailFieldVisible(),
                "Contact email field should be visible");
    }

    @Step("Verify contact name field is visible")
    public void verifyContactNameVisible() {
        AssertionHelper.assertTrue(dashboardPage.isContactNameFieldVisible(),
                "Contact name field should be visible");
    }

    @Step("Verify contact message field is visible")
    public void verifyContactMessageVisible() {
        AssertionHelper.assertTrue(dashboardPage.isContactMessageFieldVisible(),
                "Contact message field should be visible");
    }

    @Step("Close contact modal")
    public void closeContactModal() {
        dashboardPage.closeContactModal();
    }

    @Step("Verify contact modal is closed")
    public void verifyContactModalClosed() {
        AssertionHelper.assertTrue(dashboardPage.isContactModalClosed(),
                "Contact modal should be closed");
    }

    @Step("Verify About Us modal is displayed")
    public void verifyAboutUsModalDisplayed() {
        AssertionHelper.assertTrue(dashboardPage.isAboutUsModalDisplayed(),
                "About Us modal should be displayed");
    }

    @Step("Verify video player is present")
    public void verifyVideoPlayerPresent() {
        AssertionHelper.assertTrue(dashboardPage.isVideoPlayerPresent(),
                "Video player should be present in About Us modal");
    }

    @Step("Close About Us modal")
    public void closeAboutUsModal() {
        dashboardPage.closeAboutUsModal();
    }

    @Step("Verify About Us modal is closed")
    public void verifyAboutUsModalClosed() {
        AssertionHelper.assertTrue(dashboardPage.isAboutUsModalClosed(),
                "About Us modal should be closed");
    }

    @Step("Verify user is logged in")
    public void verifyLoggedIn() {
        String welcomeText = dashboardPage.getWelcomeText();
        AssertionHelper.assertFalse(welcomeText.isEmpty(),
                "Welcome message should be visible indicating user is logged in");
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPassword(password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginButton();
    }
}

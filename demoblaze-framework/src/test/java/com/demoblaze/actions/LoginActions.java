package com.demoblaze.actions;

import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.AssertionHelper;
import com.demoblaze.utils.ScreenshotUtil;
import com.demoblaze.utils.DriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginActions {

    private static final Logger log = LogManager.getLogger(LoginActions.class);
    private final LoginPage loginPage;

    public LoginActions() {
        this.loginPage = new LoginPage();
    }

    @Step("Open browser and navigate to: {url}")
    public void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        loginPage.navigateTo(url);
    }

    @Step("Verify page title is: {expectedTitle}")
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = loginPage.getPageTitle();
        log.info("Page title: '{}'", actualTitle);
        AssertionHelper.assertTrue(
                actualTitle.contains(expectedTitle),
                "Page title should contain '" + expectedTitle + "' but was '" + actualTitle + "'"
        );
    }

    @Step("Click 'Log in' navigation link")
    public void clickLoginNavLink() {
        loginPage.clickLoginNavLink();
        log.info("Login nav link clicked");
    }

    @Step("Enter username: '{username}'")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step("Perform login with username: '{username}'")
    public void performLogin(String username, String password) {
        log.info("Performing login for user: '{}'", username);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Verify user is logged in successfully")
    public void verifyLoginSuccess() {
        String welcomeMsg = loginPage.getWelcomeMessage();
        AssertionHelper.assertFalse(welcomeMsg.isEmpty(),
                "Welcome message should be visible after successful login");
        AssertionHelper.assertTrue(loginPage.isLogoutVisible(),
                "Logout button should be visible after login");

        ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), "login_success");
        log.info("Login verified successfully");
    }

    @Step("Verify welcome message contains: '{expectedMessage}'")
    public void verifyWelcomeMessage(String expectedMessage) {
        String actualMsg = loginPage.getWelcomeMessage();
        AssertionHelper.assertContains(actualMsg, expectedMessage,
                "Welcome message should contain '" + expectedMessage + "' but was '" + actualMsg + "'");
    }

    @Step("Verify logout link is visible")
    public void verifyLogoutVisible() {
        AssertionHelper.assertTrue(loginPage.isLogoutVisible(),
                "Logout link should be visible after login");
    }

    @Step("Verify alert message: '{expectedMessage}'")
    public String verifyAlertMessage(String expectedMessage) {
        String alertText = loginPage.getAlertMessage();
        if (expectedMessage != null && !expectedMessage.isEmpty()) {
            AssertionHelper.assertContains(alertText, expectedMessage,
                    "Alert message mismatch. Expected: '" + expectedMessage + "' Got: '" + alertText + "'");
        }
        ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), "alert_" + expectedMessage.replaceAll("\\s+", "_"));
        return alertText;
    }

    @Step("Accept browser alert")
    public void acceptAlert() {
        loginPage.acceptLoginAlert();
        log.info("Alert accepted");
    }

    @Step("Verify login modal is displayed")
    public void verifyLoginModalDisplayed() {
        AssertionHelper.assertTrue(loginPage.isLoginModalDisplayed(),
                "Login modal should be displayed");
    }

    @Step("Verify username field is present")
    public void verifyUsernameFieldPresent() {
        AssertionHelper.assertTrue(loginPage.isUsernameFieldPresent(),
                "Username field should be present in the login modal");
    }

    @Step("Verify password field is present")
    public void verifyPasswordFieldPresent() {
        AssertionHelper.assertTrue(loginPage.isPasswordFieldPresent(),
                "Password field should be present in the login modal");
    }

    @Step("Close login modal")
    public void closeLoginModal() {
        loginPage.closeLoginModal();
    }

    @Step("Verify login modal is closed")
    public void verifyLoginModalClosed() {
        AssertionHelper.assertTrue(loginPage.isLoginModalClosed(),
                "Login modal should be closed after clicking close button");
    }

    @Step("Verify current URL contains: '{urlFragment}'")
    public void verifyUrlContains(String urlFragment) {
        String currentUrl = loginPage.getCurrentUrl();
        AssertionHelper.assertContains(currentUrl, urlFragment,
                "Current URL should contain '" + urlFragment + "' but was: '" + currentUrl + "'");
    }

    @Step("Verify result based on expected: '{expectedResult}'")
    public void verifyLoginResult(String expectedResult) {
        if (expectedResult.startsWith("Welcome")) {

            verifyWelcomeMessage(expectedResult);
        } else {

            verifyAlertMessage(expectedResult);
            acceptAlert();
        }
    }
}

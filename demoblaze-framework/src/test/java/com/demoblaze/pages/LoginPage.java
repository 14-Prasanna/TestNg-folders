package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login2")
    private WebElement loginNavLink;

    @FindBy(id = "logInModal")
    private WebElement loginModal;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessageText;

    @FindBy(id = "logout2")
    private WebElement logoutLink;

    @FindBy(xpath = "//button[contains(@class,'close') and @data-target='#logInModal']")
    private WebElement loginModalCloseButton;

    private final By loginModalLocator = By.id("logInModal");
    private final By loginModalCloseBtn = By.xpath("//div[@id='logInModal']//button[@class='close']");

    public void clickLoginNavLink() {
        log.info("Clicking 'Log in' navigation link");
        click(loginNavLink);
        WaitUtil();
    }

    public void enterUsername(String username) {
        log.info("Entering username: '{}'", username);
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        type(passwordField, password);
    }

    public void clickLoginButton() {
        log.info("Clicking Login button");
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getWelcomeMessage() {
        try {
            com.demoblaze.utils.WaitUtil.waitForVisibility(driver, welcomeMessageText);
            String msg = welcomeMessageText.getText().trim();
            log.info("Welcome message: '{}'", msg);
            return msg;
        } catch (Exception e) {
            log.warn("Welcome message not found: {}", e.getMessage());
            return "";
        }
    }

    public boolean isLogoutVisible() {
        boolean visible = isDisplayed(logoutLink);
        log.info("Logout link visible: {}", visible);
        return visible;
    }

    public boolean isLoginModalDisplayed() {
        try {
            com.demoblaze.utils.WaitUtil.waitForVisibility(driver, loginModal);
            return loginModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameFieldPresent() {
        return isDisplayed(usernameField);
    }

    public boolean isPasswordFieldPresent() {
        return isDisplayed(passwordField);
    }

    public void closeLoginModal() {
        log.info("Closing login modal");
        click(loginModalCloseBtn);
        com.demoblaze.utils.WaitUtil.waitForInvisibility(driver, loginModalLocator);
    }

    public boolean isLoginModalClosed() {
        return !isDisplayed(loginModalLocator);
    }

    public String getAlertMessage() {
        String alertText = getAlertText();
        log.info("Alert message: '{}'", alertText);
        return alertText;
    }

    public void acceptLoginAlert() {
        acceptAlert();
    }

    private void WaitUtil() {
        com.demoblaze.utils.WaitUtil.shortPause(800);
    }
}

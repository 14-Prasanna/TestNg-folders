package com.demoblaze.stepDefinitions;

import com.demoblaze.actions.LoginActions;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginStepDefinitions {

    private static final Logger log = LogManager.getLogger(LoginStepDefinitions.class);
    private final LoginActions loginActions = new LoginActions();

    @Given("I open the browser and navigate to {string}")
    public void i_open_browser_and_navigate_to(String url) {
        log.info("[Step] I open the browser and navigate to: {}", url);
        loginActions.navigateTo(url);
    }

    @And("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        log.info("[Step] Verify page title: {}", expectedTitle);
        loginActions.verifyPageTitle(expectedTitle);
    }

    @And("I click on the {string} navigation link")
    public void i_click_on_the_navigation_link(String linkText) {
        log.info("[Step] Click navigation link: {}", linkText);
        if ("Log in".equals(linkText)) {
            loginActions.clickLoginNavLink();
        }
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        log.info("[Step] Enter username: '{}', password provided: {}", username, !password.isEmpty());
        loginActions.enterUsername(username);
        loginActions.enterPassword(password);
    }

    @And("I click the Login button")
    public void i_click_the_login_button() {
        log.info("[Step] Click Login button");
        loginActions.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        log.info("[Step] Verify login success");
        loginActions.verifyLoginSuccess();
    }

    @And("the welcome message should display {string}")
    public void the_welcome_message_should_display(String expectedMessage) {
        log.info("[Step] Verify welcome message: {}", expectedMessage);
        loginActions.verifyWelcomeMessage(expectedMessage);
    }

    @And("the logout option should be visible")
    public void the_logout_option_should_be_visible() {
        log.info("[Step] Verify logout visible");
        loginActions.verifyLogoutVisible();
    }

    @Then("an alert should appear with message {string}")
    public void an_alert_should_appear_with_message(String expectedMessage) {
        log.info("[Step] Verify alert message: {}", expectedMessage);
        loginActions.verifyAlertMessage(expectedMessage);
    }

    @And("I accept the alert")
    public void i_accept_the_alert() {
        log.info("[Step] Accept alert");
        loginActions.acceptAlert();
    }

    @Then("the login modal should be displayed")
    public void the_login_modal_should_be_displayed() {
        log.info("[Step] Verify login modal displayed");
        loginActions.verifyLoginModalDisplayed();
    }

    @And("the username field should be present")
    public void the_username_field_should_be_present() {
        log.info("[Step] Verify username field present");
        loginActions.verifyUsernameFieldPresent();
    }

    @And("the password field should be present")
    public void the_password_field_should_be_present() {
        log.info("[Step] Verify password field present");
        loginActions.verifyPasswordFieldPresent();
    }

    @When("I close the login modal")
    public void i_close_the_login_modal() {
        log.info("[Step] Close login modal");
        loginActions.closeLoginModal();
    }

    @Then("the login modal should be closed")
    public void the_login_modal_should_be_closed() {
        log.info("[Step] Verify login modal closed");
        loginActions.verifyLoginModalClosed();
    }

    @Then("the current URL should contain {string}")
    public void the_current_url_should_contain(String urlFragment) {
        log.info("[Step] Verify URL contains: {}", urlFragment);
        loginActions.verifyUrlContains(urlFragment);
    }

    @Then("I should see the result {string}")
    public void i_should_see_the_result(String expectedResult) {
        log.info("[Step] Verify result: {}", expectedResult);
        loginActions.verifyLoginResult(expectedResult);
    }
}

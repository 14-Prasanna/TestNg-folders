package com.demoblaze.stepDefinitions;

import com.demoblaze.actions.DashboardActions;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardStepDefinitions {

    private static final Logger log = LogManager.getLogger(DashboardStepDefinitions.class);
    private final DashboardActions dashboardActions = new DashboardActions();

    @And("I should be logged in successfully")
    public void i_should_be_logged_in_successfully_dashboard() {
        log.info("[Step] Verify user is logged in (dashboard context)");
        dashboardActions.verifyLoggedIn();
    }

    @When("I navigate to the Home page")
    public void i_navigate_to_the_home_page() {
        log.info("[Step] Navigate to Home page");
        dashboardActions.navigateToHome();
    }

    @When("I click on the {string} navigation link")
    public void i_click_on_the_nav_link(String linkText) {
        log.info("[Step] Click nav link: {}", linkText);
        dashboardActions.clickNavLink(linkText);
    }

    @Then("the navigation bar should contain {string}")
    public void the_navigation_bar_should_contain(String text) {
        log.info("[Step] Verify nav bar contains: {}", text);
        dashboardActions.verifyNavBarContains(text);
    }

    @And("the navigation bar should contain {string}")
    public void and_the_navigation_bar_should_contain(String text) {
        log.info("[Step] And nav bar contains: {}", text);
        dashboardActions.verifyNavBarContains(text);
    }

    @Then("I should see the {string} category in the sidebar")
    public void i_should_see_category_in_sidebar(String category) {
        log.info("[Step] Verify category visible: {}", category);
        dashboardActions.verifyCategoryVisible(category);
    }

    @And("I should see the {string} category in the sidebar")
    public void and_i_should_see_category_in_sidebar(String category) {
        log.info("[Step] And category visible: {}", category);
        dashboardActions.verifyCategoryVisible(category);
    }

    @When("I click on the {string} category")
    public void i_click_on_the_category(String category) {
        log.info("[Step] Click category: {}", category);
        dashboardActions.clickCategory(category);
    }

    @Then("the product list should refresh")
    public void the_product_list_should_refresh() {
        log.info("[Step] Product list refreshed");
        dashboardActions.verifyProductListRefreshed();
    }

    @And("at least one product should be displayed under {string}")
    public void at_least_one_product_should_be_displayed_under(String category) {
        log.info("[Step] Verify products displayed under: {}", category);
        dashboardActions.verifyProductsDisplayed(category);
    }

    @Then("the carousel should be displayed")
    public void the_carousel_should_be_displayed() {
        log.info("[Step] Verify carousel displayed");
        dashboardActions.verifyCarouselDisplayed();
    }

    @And("the next carousel button should be clickable")
    public void the_next_carousel_button_should_be_clickable() {
        log.info("[Step] Verify carousel next button clickable");
        dashboardActions.verifyCarouselNextClickable();
    }

    @When("I click the next carousel button")
    public void i_click_the_next_carousel_button() {
        log.info("[Step] Click carousel next");
        dashboardActions.clickCarouselNext();
    }

    @Then("the carousel should advance to the next slide")
    public void the_carousel_should_advance_to_the_next_slide() {
        log.info("[Step] Verify carousel advanced");
        dashboardActions.verifyCarouselAdvanced();
    }

    @When("I click on the first product in the list")
    public void i_click_on_the_first_product() {
        log.info("[Step] Click first product");
        dashboardActions.clickFirstProduct();
    }

    @Then("I should be navigated to the product detail page")
    public void i_should_be_navigated_to_product_detail_page() {
        log.info("[Step] Verify on product detail page");
        dashboardActions.verifyOnProductDetailPage();
    }

    @And("the product name should be visible")
    public void the_product_name_should_be_visible() {
        log.info("[Step] Verify product name visible");
        dashboardActions.verifyProductNameVisible();
    }

    @And("the product price should be visible")
    public void the_product_price_should_be_visible() {
        log.info("[Step] Verify product price visible");
        dashboardActions.verifyProductPriceVisible();
    }

    @And("the {string} button should be present")
    public void the_button_should_be_present(String buttonText) {
        log.info("[Step] Verify button present: {}", buttonText);
        if ("Add to cart".equals(buttonText)) {
            dashboardActions.verifyAddToCartPresent();
        }
    }

    @When("I search for product {string} in the catalog")
    public void i_search_for_product_in_catalog(String productName) {
        log.info("[Step] Search for product: {}", productName);
        dashboardActions.searchForProduct(productName);
    }

    @Then("the product {string} should be visible")
    public void the_product_should_be_visible(String productName) {
        log.info("[Step] Verify product visible: {}", productName);
        dashboardActions.verifyProductVisible(productName);
    }

    @And("the price should be displayed for {string}")
    public void the_price_should_be_displayed_for(String productName) {
        log.info("[Step] Verify price for: {}", productName);
        dashboardActions.verifyPriceDisplayed(productName);
    }

    @When("I click on the {string} link")
    public void i_click_on_the_link(String linkText) {
        log.info("[Step] Click link: {}", linkText);
        if ("Log out".equals(linkText)) {
            dashboardActions.clickLogout();
        }
    }

    @Then("the {string} navigation link should be visible")
    public void the_navigation_link_should_be_visible(String linkText) {
        log.info("[Step] Verify nav link visible: {}", linkText);
        if ("Log in".equals(linkText)) {
            dashboardActions.verifyLoginLinkVisible();
        } else if ("Sign up".equals(linkText)) {
            dashboardActions.verifySignupLinkVisible();
        }
    }

    @And("the {string} navigation link should be visible")
    public void and_the_navigation_link_should_be_visible(String linkText) {
        log.info("[Step] And nav link visible: {}", linkText);
        the_navigation_link_should_be_visible(linkText);
    }

    @And("I should not see {string} in the nav bar")
    public void i_should_not_see_in_nav_bar(String text) {
        log.info("[Step] Verify '{}' NOT in nav bar", text);
        dashboardActions.verifyWelcomeNotVisible();
    }

    @Then("the contact modal should be displayed")
    public void the_contact_modal_should_be_displayed() {
        log.info("[Step] Verify contact modal displayed");
        dashboardActions.verifyContactModalDisplayed();
    }

    @And("the contact email field should be visible")
    public void the_contact_email_field_should_be_visible() {
        dashboardActions.verifyContactEmailVisible();
    }

    @And("the contact name field should be visible")
    public void the_contact_name_field_should_be_visible() {
        dashboardActions.verifyContactNameVisible();
    }

    @And("the message field should be visible")
    public void the_message_field_should_be_visible() {
        dashboardActions.verifyContactMessageVisible();
    }

    @When("I close the contact modal")
    public void i_close_the_contact_modal() {
        dashboardActions.closeContactModal();
    }

    @Then("the contact modal should be closed")
    public void the_contact_modal_should_be_closed() {
        dashboardActions.verifyContactModalClosed();
    }

    @Then("the about us modal should be displayed")
    public void the_about_us_modal_should_be_displayed() {
        dashboardActions.verifyAboutUsModalDisplayed();
    }

    @And("the video player should be present")
    public void the_video_player_should_be_present() {
        dashboardActions.verifyVideoPlayerPresent();
    }

    @When("I close the about us modal")
    public void i_close_the_about_us_modal() {
        dashboardActions.closeAboutUsModal();
    }

    @Then("the about us modal should be closed")
    public void the_about_us_modal_should_be_closed() {
        dashboardActions.verifyAboutUsModalClosed();
    }
}

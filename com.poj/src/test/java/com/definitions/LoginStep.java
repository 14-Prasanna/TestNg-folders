package com.definitions;

import com.actions.HomePageAction;
import com.actions.LoginPageAction;
import com.utilities.HelpClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.FileNotFoundException;

public class LoginStep {

    LoginPageAction loginPageAction = new LoginPageAction();
    HomePageAction homePageAction = new HomePageAction();

    @Given("User is on HRMLogin page {string}")
    public void user_is_on_hrm_login_page(String string) {
        HelpClass.openPage(string);
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() throws FileNotFoundException {
        loginPageAction.login();
    }

    @Then("user should be able to login successfully and should see Dashboard page")
    public void user_should_be_able_to_login_successfully_and_should_see_dashboard_page() {
        String value = homePageAction.getHomePage();

        Assert.assertEquals(value, "Dashboard");
        System.out.println("The Dashboard page is navigated Successfully");
    }

    @Then("user should be able to login successfully and should see error message")
    public void userShouldBeAbleToLoginSuccessfullyAndShouldSeeErrorMessage() {


    }
}

package com.definitions;


import org.testng.Assert;

import com.actions.DashboardPageAction;
import com.actions.LoginPageAction;
import com.utilities.HelperClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageDefinitions {
	LoginPageAction objLogin=new LoginPageAction();
	DashboardPageAction objDashboard=new DashboardPageAction();
	@Given("user is on HRM Login page {string}")
	public void user_is_on_hrm_login_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    HelperClass.openPage(string);
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	   objLogin.login();
	}

	@Then("user should able to login successfully and see the Dashboard")
	public void user_should_able_to_login_successfully_and_see_the_dashboard() {
	    // Write code here that turns the phrase above into concrete actions
	    Assert.assertEquals("Dashboard", objDashboard.getDashboardText());
	}
	@When("user enters username as {string} and password as {string}")
	public void user_enters_username_as_and_password_as(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    objLogin.login1(string,string2);
	}

	@Then("User can see the Error message as\"Invalid Credentials\"")
	public void user_can_see_the_error_message_as_invalid_credentials() {
	    // Write code here that turns the phrase above into concrete actions
	    Assert.assertEquals("Invalid credentials",objLogin.getAlertMessage());
	}

}


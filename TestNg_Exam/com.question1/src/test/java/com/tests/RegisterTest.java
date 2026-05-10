package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "Register", dataProviderClass = com.utils.LoginDataProvider.class, priority = 1)
    public void CheckValid(String username, String email, String password) {

        RegisterPage registerPage = new RegisterPage(getDriver());
        String result = registerPage.RegisterDetails(username, email);

        try {
            Assert.assertEquals(result, "ENTER ACCOUNT INFORMATION");
            logger.info("Navigated to Account Information page successfully");

            String accountResult = registerPage.AccountCreation(username,password,"Venkatesh","tfeq wdgsowv",0,"Tamilnadu","Trichy","620006","1234567890");

            Assert.assertEquals(accountResult, "ACCOUNT CREATED!");
            logger.info("Account created successfully");

        } catch (AssertionError e) {
            logger.error("Test failed during registration flow");
            throw e;
        }
    }

    @Test(dataProvider = "Register", dataProviderClass = com.utils.LoginDataProvider.class, dependsOnMethods = "CheckValid")
    public void inValidCheck(String username, String email, String password) {

        RegisterPage registerPage = new RegisterPage(getDriver());
        String result = registerPage.RegisterDetails(username, email);

        try {
            Assert.assertEquals(result, "Email Address already exist!");
            logger.info("Proper error shown for duplicate email");
        } catch (AssertionError e) {
            logger.error("Duplicate email was accepted incorrectly");
            throw e;
        }
    }
}
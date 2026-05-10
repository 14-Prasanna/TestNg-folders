package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "LoginValid", dataProviderClass = com.utils.LoginDataProvider.class)
    public void loginValid(String user, String pass) {
        LoginPage page = new LoginPage(getDriver());
        String result = page.logvalid(user, pass);

        try {
            Assert.assertEquals(result, "Logout");
            logger.info("The login is successfully done");
        } catch (AssertionError e) {
            logger.error("The login is unsuccessfully error");
            throw e;
        }
    }

    @Test(dataProvider = "invalid", dataProviderClass = com.utils.LoginDataProvider.class)
    public void loginInValid(String user, String pass) {
        LoginPage page = new LoginPage(getDriver());
        String result = page.Invalid(user, pass);

        try {
            Assert.assertEquals(result, "Your email or password is incorrect!");
            logger.info("The invalid email and password is not accepted");
        } catch (AssertionError e) {
            logger.error("The invalid email and error is accepted");
            throw e;
        }
    }
}
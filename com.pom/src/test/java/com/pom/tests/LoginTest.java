package com.pom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pom.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginValid() {

        LoginPage objlogin = new LoginPage(getDriver());
        String loginPage = objlogin.getTitles();

        Assert.assertTrue(loginPage.contains("Username : Admin"));
        System.out.println("Login page opened successfully");
    }
}
package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	
	
    @Test(dataProvider = "valid", dataProviderClass = com.utils.LoginDataProvider.class)
    public void loginValid(String user, String pass) {

        LoginPage objlogin = new LoginPage(getDriver());
        String loginPage = objlogin.getTitle();
        
        Assert.assertTrue(loginPage.contains("Username : Admin"));
        logger.info("Login page opened successfully");
        
        
        String resultUrl = objlogin.loginValid(user, pass);
        
        try {
        		Assert.assertEquals(resultUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        		logger.info("Correct details provided and navigated to next page");
        }
        catch(AssertionError e) {
        		logger.error("The invalid login details provided but accpted");
        		throw e;
        }
    }
    
    
    @Test(dataProvider = "invalid", dataProviderClass = com.utils.LoginDataProvider.class)
    public void loginInValid(String user, String pass) {

        LoginPage objlogin = new LoginPage(getDriver());
        String loginPage = objlogin.getTitle();
        
        Assert.assertTrue(loginPage.contains("Username : Admin"));
        logger.info("Login page opened successfully");
        
        
        String result = objlogin.LoginInvalid(user, pass);
        
        try {
        		Assert.assertEquals(result, "Invalid credentials");
        		logger.info("Invalid login the message pop up successfully");
        }
        catch(AssertionError e) {
        		logger.error("The invalid login is accepted error");
        		throw e;
        }
    }
    
    

}

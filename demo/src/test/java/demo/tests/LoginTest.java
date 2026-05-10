package demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import demo.pages.LoginPage;
import demo.utils.LoginDataProvider;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "valid", dataProviderClass = LoginDataProvider.class)
    public void loginValid(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());
        String result = loginPage.LoginValid(username, password);

        Assert.assertEquals(result, "blocks", "Valid login failed!");
        System.out.println("User logged in successfully");

        
        loginPage.logout.click();
    }

    @Test(dataProvider = "invalid", dataProviderClass = LoginDataProvider.class)
    public void loginInValid(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());
        String result = loginPage.LoginInvalid(username, password);

        if (result.equals("Wrong password.")) {
            Assert.assertEquals(result, "Wrong password.");
            System.out.println("Invalid password is not accepted by the system");
        } 
        else if (result.equals("User does not exist.")) {
            Assert.assertEquals(result, "User does not exist.");
            System.out.println("Invalid username is not accepted by the system");
        } 
        else {
            Assert.fail("Unexpected alert message: " + result);
        }
    }
}
package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.DashboardPage;
import com.pages.LoginPage;

public class DashboardTest extends BaseTest {
	
    @Test
    public void dashboard() {

        LoginPage objlogin = new LoginPage(getDriver());
        objlogin.loginValid("Admin", "admin123");

        DashboardPage objdash = new DashboardPage(getDriver());
        String titlepage = objdash.getPage();

        Assert.assertEquals(titlepage, "Dashboard");
        logger.info("Dashboard navigated successfully");
    }

}

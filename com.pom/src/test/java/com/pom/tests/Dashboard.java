package com.pom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pom.pages.DashboardPage;
import com.pom.pages.LoginPage;

public class Dashboard extends BaseTest {

    @Test
    public void dashboard() {

        LoginPage objlogin = new LoginPage(getDriver());
        objlogin.login("Admin", "admin123");

        DashboardPage objdash = new DashboardPage(getDriver());
        String titlepage = objdash.Homepage();

        Assert.assertEquals(titlepage, "Dashboard");
        System.out.println("Dashboard navigated successfully");
    }
}
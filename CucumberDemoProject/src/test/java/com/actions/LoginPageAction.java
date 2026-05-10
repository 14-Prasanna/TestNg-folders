package com.actions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;

import com.pages.LoginPageLocators;
import com.utilities.HelperClass;

public class LoginPageAction {
	LoginPageLocators lpl = null;
	String Username, Password;

	public LoginPageAction() {
		this.lpl = new LoginPageLocators();
		PageFactory.initElements(HelperClass.getDriver(), lpl);
	}

	public void setUsername(String Username) {
		lpl.username.sendKeys(Username);
	}

	public void setPassword(String Password) {
		lpl.password.sendKeys(Password);
	}

	public void clickLogin() {
		lpl.LoginBtn.click();
	}
	public String getAlertMessage() {
		return lpl.alert.getText();
	}
	public void login() {
		File file = new File("C:\\Users\\tamil\\git\\repository19\\DemoBlazeCucumberProject\\src\\test\\resources\\testData.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Username = prop.getProperty("username");
		Password = prop.getProperty("password");
		this.setUsername(Username);
		this.setPassword(Password);
		this.clickLogin();
	}
	public void login1(String username,String password) {
		this.setUsername(username);
		this.setPassword(password);
		this.clickLogin();
	}
}

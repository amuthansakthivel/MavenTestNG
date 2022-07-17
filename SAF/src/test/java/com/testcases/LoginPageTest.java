package com.testcases;

import com.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class LoginPageTest extends BaseTest{

	LoginPage loginPage = new LoginPage();

	@Test
	public void login_validUsername_invalidPassword(Hashtable<String,String> data) {
		loginPage = new LoginPage();
		loginPage.login(data.get("username"),data.get("password"));
		// Expectation is -> Error should be displayed.
		Assert.assertEquals(loginPage.get_login_error_msg(), "Epic sadface: Username and password do not match any user in this service");
	}

	@Test
	public void login_validUsername_validPassword(Hashtable<String,String> data) {
		loginPage = new LoginPage();
		loginPage.login(data.get("username"),data.get("password"));
		// Expectation is -> Products page should be displayed.
		Assert.assertEquals(loginPage.getProductsPageTitle(), "PRODUCTS");
	}

	@Test
	public void login_invalidUsername_validPassword(Hashtable<String,String> data) {
		loginPage = new LoginPage();
		loginPage.login(data.get("username"),data.get("password"));
		// Expectation is -> Error should be displayed.
		Assert.assertEquals(loginPage.get_login_error_msg(), "Epic sadface: Username and password do not match any user in this service");
	}
}

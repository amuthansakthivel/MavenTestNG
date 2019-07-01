package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.utils.ReadPropertyFile;
import com.utils.TestUtils;

public class LoginPage {
	
	@FindBy(name="username")
	WebElement txtbox_username;
	
	@FindBy(name="password")
	WebElement txtbox_password;
	
	@FindBy(xpath="//*[text()='LOGIN']")
	WebElement btn_login;

	public LoginPage() {
		PageFactory.initElements(Driver.driver, this);
	}
	
	public  HomePage login(String username,String password) {
		TestUtils.sendkeys(txtbox_username,username);
		TestUtils.sendkeys(txtbox_password,password);
		TestUtils.click(btn_login);
		return new HomePage();
	}
}

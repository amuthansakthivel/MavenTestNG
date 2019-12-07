package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.listener.ListenerClass;
import com.reports.ExtentReport;
import com.utils.ReadPropertyFile;

import com.utils.TestUtils;

public class LoginPage extends BasePage{
	
	@FindBy(name="username")
	WebElement txtbox_username;
	
	@FindBy(name="password")
	WebElement txtbox_password;
	
	@FindBy(xpath="//*[text()='LOGIN']")
	WebElement btn_login;

	
	
	public  HomePage login() {
		sendkeys(txtbox_username,TestUtils.getCellContent("TestData", ListenerClass.getTestcaseName(), "username"));
		sendkeys(txtbox_password,TestUtils.getCellContent("TestData", ListenerClass.getTestcaseName(), "password"));
		click(btn_login);
		return new HomePage();
	}
}

package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// Capture all the Elements in the Login page

	@FindBy(xpath="//input[@id='user-name']")
	WebElement txtbox_username;

	@FindBy(xpath="//input[@id='password']")
	WebElement txtbox_password;

	@FindBy(xpath="//input[@id='login-button']")
	WebElement btn_password;

	@FindBy(xpath="//div[@class='error-message-container error']/h3")
	WebElement txt_errormsg;

	@FindBy(xpath="//span[@class='title']")
	WebElement heading_productsPage;

	// Capture all the methods in the Login page
	
	public void login(String user, String pwd)
	{
		sendkeys(txtbox_username,user);
		sendkeys(txtbox_password,pwd);
		click(btn_password);
	}

	public String get_login_error_msg()
	{
		return txt_errormsg.getText();
	}

	public String getProductsPageTitle()
	{
		return heading_productsPage.getText();
	}

}

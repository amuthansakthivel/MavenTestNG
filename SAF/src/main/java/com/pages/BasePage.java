package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.browser.DriverManager;

public class BasePage {
	
	protected BasePage(){
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

}

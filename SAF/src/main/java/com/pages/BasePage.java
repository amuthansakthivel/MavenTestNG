package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;

public class BasePage {
	
	protected BasePage(){
		PageFactory.initElements(Driver.getDriver(), this);
	}

}

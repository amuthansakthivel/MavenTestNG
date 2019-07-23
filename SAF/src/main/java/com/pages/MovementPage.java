package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;

public class MovementPage {

	@FindBy(name="username")
	WebElement txtbox_username;

	public MovementPage() {
		PageFactory.initElements(Driver.driver, this);
	}
}

package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.browser.Driver;

public class GlobalViewPage {

	@FindBy(name="username")
	WebElement txtbox_username;

	public GlobalViewPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

}

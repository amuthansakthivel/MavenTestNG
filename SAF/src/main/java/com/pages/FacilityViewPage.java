package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.utils.SeleniumUtils;
import com.utils.TestUtils;

public class FacilityViewPage {

	@FindBy(xpath="//input[@placeholder='Shipment ID']")
	WebElement txtbox_shipmentID;
	
	@FindBy(xpath="//input[@placeholder='Handling Unit ID']")
	WebElement txtbox_handlingunitID;
	
	@FindBy(xpath="//input[@placeholder='Account Number']")
	WebElement txtbox_accountnumber;

	public FacilityViewPage() {
		PageFactory.initElements(Driver.driver, this);
	}
	
	public void entershipmentID() {
		SeleniumUtils.sendkeys(txtbox_shipmentID, "abc123");
	}
	
}

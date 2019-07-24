package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.relevantcodes.extentreports.LogStatus;
import com.reports.ExtentReport;
import com.utils.SeleniumUtils;
import com.utils.TestUtils;

public class HomePage {

	@FindBy(xpath="//span[text()='Global View']")
	WebElement lnk_globalview;
	
	@FindBy(xpath="//span[text()='Facility View']")
	WebElement lnk_facilityview;
	
	@FindBy(xpath="//span[text()='Movement']")
	WebElement lnk_movement;
	
	@FindBy(xpath="//span[text()='Account View']")
	WebElement lnk_accountview;
	
	@FindBy(xpath="//a[@data-toggle='dropdown']/div[text()='asakthiv']")
	WebElement lnk_asakthiv;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement lnk_logout;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn_logoutonframe;
	
	@FindBy(xpath="//span[text()='EXCEPTION PER COUNTRY FOR']")
	WebElement txt_exceptionpercountryfor;

	@FindBy(name="r")
	WebElement searchBox;

	
	public HomePage() {
		PageFactory.initElements(Driver.driver, this);
	}
	
	
	public void logout() {
		SeleniumUtils.click(lnk_asakthiv);
		SeleniumUtils.click(lnk_logout);
		SeleniumUtils.click(btn_logoutonframe);
	}
	
	public  GlobalViewPage clickOnGlobalView() {
		SeleniumUtils.click(lnk_globalview);
		return new GlobalViewPage();
	}
	public FacilityViewPage clickOnFacilityView() {
		SeleniumUtils.click(lnk_facilityview);
		return new FacilityViewPage();
	}
	public MovementPage clickOnMovement() {
		SeleniumUtils.click(lnk_movement);
		return new MovementPage();
	}
	public AccountViewPage clickOnAccountView() {
		SeleniumUtils.click(lnk_accountview);
		return new AccountViewPage();
	}
	
	public void searchOnGoogle() {
		SeleniumUtils.sendkeys(searchBox, "automation");
	}
	
	public boolean checkDefaultTabSelectedIsGlobalView() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(txt_exceptionpercountryfor.isDisplayed()==true) {
			com.reports.LogStatus.pass("Text : Exception country for is verified in the Global view");
				}
		TestUtils.takeScreenshot();
		return txt_exceptionpercountryfor.isDisplayed();
	}
}

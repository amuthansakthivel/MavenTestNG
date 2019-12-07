package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browser.Driver;
import com.browser.DriverManager;
import com.relevantcodes.extentreports.LogStatus;
import com.reports.ExtentReport;
import com.utils.DynamicXpath;
import com.utils.TestUtils;

public class HomePage extends BasePage {

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
	
	

	@FindBy(name="q")
	WebElement searchBox;
	
	
	
	public static String txtbox_searchbox="//*[@name='%replaceable%']";

	
	
	
	
	public void logout() {
		String a="abcd";
		click(lnk_asakthiv);
		click(lnk_logout);
		click(btn_logoutonframe);
		
		//clicking on dynamic element
		searchBox.findElement(By.xpath("/a/span[@id='"+a+"']")).click();
	}
	
	
	
	
	public void searchOnGoogle(String value) {
		//SeleniumUtils.sendkeys(searchBox, "automation");
		String newxpath=DynamicXpath.get(txtbox_searchbox, "q");
		sendkeys(DriverManager.getDriver().findElement(By.xpath(newxpath)),value);
	}
	
	
}

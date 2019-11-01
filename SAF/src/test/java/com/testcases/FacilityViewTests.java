package com.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver.Timeouts;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.browser.Driver;
import com.listener.ListenerClass;
import com.pages.FacilityViewPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.reports.ExtentReport;
import com.utils.TestUtils;

import okio.Timeout;

public class FacilityViewTests extends BaseTest{
	
	LoginPage loginpage;
	HomePage homepage;
	FacilityViewPage facilityviewpage;
	
	

	
	
	
	@Test
	public void validateEnteringShipmentID() {
		loginpage=new LoginPage();
		homepage=loginpage.login();
		facilityviewpage=homepage.clickOnFacilityView();
		facilityviewpage.entershipmentID();
		homepage.logout();
	}
	
	@Test
	public void checkWhetherGlobalViewIsSelectedByDefault() {
		loginpage=new LoginPage();
		homepage=loginpage.login();
		Assert.assertTrue(homepage.checkDefaultTabSelectedIsGlobalView());
		homepage.logout();
	}
	
	@Test
	public void test1(Hashtable<String,String> data) {
		homepage=new HomePage();
		homepage.searchOnGoogle(data.get("valueforsearch"));
		System.out.println("username :" + data.get("username"));
		//Assert.assertEquals(1, 2);
		
	}
	@Test
	public void test2(Hashtable<String,String> data) {
		homepage=new HomePage();
		homepage.searchOnGoogle(data.get("valueforsearch"));
		//Assert.assertEquals(1, 2);
		
	}
	
	
	
	

}

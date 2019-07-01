package com.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.browser.Driver;
import com.pages.FacilityViewPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.reports.ExtentReport;
import com.utils.TestUtils;

public class FacilityViewTests {
	
	LoginPage loginpage;
	HomePage homepage;
	FacilityViewPage facilityviewpage;
	
	
	@BeforeMethod
	public void setUp() {
		Driver.initialize();
	}
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		ExtentReport.initialize();
	}
	
	@AfterSuite
	public void afterSuite() {
		ExtentReport.report.flush();
	}
	
	
	@Test(description= "Validate whether the user can able to enter shipmentID",enabled=true)
	public void validateEnteringShipmentID(Method m) {
		loginpage=new LoginPage();
		homepage=loginpage.login(TestUtils.getCellContent("testdata", m.getName().toString(), "username"),
				TestUtils.getCellContent("testdata", m.getName(), "password"));
		facilityviewpage=homepage.clickOnFacilityView();
		facilityviewpage.entershipmentID();
		homepage.logout();
	}
	
	@Test(description= "Validate whether the default view selected is Global View",enabled=true)
	public void checkWhetherGlobalViewIsSelectedByDefault(Method m) {
		loginpage=new LoginPage();
		homepage=loginpage.login(TestUtils.getCellContent("testdata", m.getName().toString(), "username"),
				TestUtils.getCellContent("testdata", m.getName(), "password"));
		Assert.assertTrue(homepage.checkDefaultTabSelectedIsGlobalView());
		homepage.logout();
	}
	
	
	
	@AfterMethod
	public void wrapUp() {
		Driver.driver.close();
	}

}

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
import com.listener.ListenerClass;
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
	public void test1() {
		Assert.assertEquals(1, 1);
	}
	@Test
	public void test2() {
		Assert.assertEquals(1, 2);
	}
	
	
	
	@AfterMethod
	public void wrapUp() {
		Driver.driver.close();
	}

}

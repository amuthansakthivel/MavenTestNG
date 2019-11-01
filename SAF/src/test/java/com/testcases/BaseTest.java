package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.browser.Driver;
import com.reports.ExtentReport;
import com.utils.TestUtils;

public class BaseTest {
	
	@BeforeMethod
	public void setUp() {
		Driver.initialize();
	}
	
	@AfterMethod
	public void wrapUp() {
		Driver.getDriver().close();
	}
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		ExtentReport.initialize();
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		
		ExtentReport.report.flush();
		TestUtils.sendEmailWithResults();
	}

}

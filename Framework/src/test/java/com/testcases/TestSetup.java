package com.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.browser.Driver;
import com.reports.ExtentReport;

public class TestSetup {
	
	@BeforeSuite
	public void beforesuite() {
		ExtentReport.initialize();
		ExtentReport.logger=ExtentReport.report.startTest("AQCC Test");
		
	}
	
	@AfterSuite
	public void aftersuite() {
		
		ExtentReport.report.flush();
	}

}

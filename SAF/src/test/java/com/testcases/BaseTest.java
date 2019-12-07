package com.testcases;

import java.awt.Desktop;
import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.browser.Driver;
import com.browser.DriverManager;
import com.browser.RemoteConfiguration;
import com.reports.ExtentReport;
import com.utils.ReadPropertyFile;
import com.utils.TestUtils;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		try {
			Driver.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void wrapUp() {
		DriverManager.getDriver().close();
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		ExtentReport.initialize();
		if(ReadPropertyFile.get("RunMode").equalsIgnoreCase("Remote"))
			RemoteConfiguration.setUpRemote();
	}

	@AfterSuite
	public void afterSuite() throws Exception {

		ExtentReport.report.flush();
		TestUtils.sendEmailWithResults();
		if(ReadPropertyFile.get("RunMode").equalsIgnoreCase("Remote")) {
			RemoteConfiguration.shutDownRemote();
		}
		
		File htmlFile = new File(ExtentReport.extentreportpath);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

}

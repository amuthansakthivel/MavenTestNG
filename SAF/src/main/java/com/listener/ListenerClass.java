package com.listener;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.reports.ExtentReport;
import com.reports.LogStatus;

public class ListenerClass implements ITestListener{
	public static String TestcaseName;

	public void onTestStart(ITestResult result) {
		TestcaseName =result.getMethod().getDescription();
		ExtentReport.logger=ExtentReport.report.startTest(TestcaseName);
		LogStatus.pass("Test Case "+TestcaseName+ " is started successfully");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		LogStatus.pass(result.getMethod().getDescription()+ " test case is passed");
		ExtentReport.report.endTest(ExtentReport.logger);
	}

	public void onTestFailure(ITestResult result) {
		LogStatus.fail(result.getMethod().getDescription()+ " is failed");
		LogStatus.fail(result.getThrowable().toString());
		ExtentReport.report.endTest(ExtentReport.logger);
		
	}

	public void onTestSkipped(ITestResult result) {
		
		LogStatus.skip(result.getMethod().getDescription()+ " is skipped");
		
		ExtentReport.report.endTest(ExtentReport.logger);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentReport.logger);
	}

	public void onStart(ITestContext context) {
	
		
	}

	public void onFinish(ITestContext context) {
		ExtentReport.report.endTest(ExtentReport.logger);
		
	}



}

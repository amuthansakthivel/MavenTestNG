package com.reports;

import com.utils.TestUtils;

public class LogStatus {

	private LogStatus() {
		//private to avoid initialization
	}
	public static void pass(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.PASS, message);
	}
	
	public static void fail(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}
	
	public static void fail(Exception message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}
	
	public static void fail(AssertionError a)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.FAIL, a);
	}
	
	public static void info(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.INFO, message);
	}
	
	public static void error(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.ERROR, message);
	}
	
	public static void fatal(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.FATAL, message);
	}
	
	public static void skip(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.SKIP, message);
	}
	
	public static void unknown(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.UNKNOWN, message);
	}
	
	public static void warning(String message)
	{
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.WARNING, message);
	}
	public static void pass(String string, String addScreenCapture) {
		
		ExtentReport.logger.log(com.relevantcodes.extentreports.LogStatus.PASS, string , ExtentReport.logger.addScreenCapture(addScreenCapture));
	}

}

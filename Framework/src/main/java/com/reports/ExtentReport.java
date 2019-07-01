package com.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.utils.ReadPropertyFile;

public class ExtentReport {

	public static ExtentReports report=null;
	public static ExtentTest logger=null;

	//To avoid external initialization
	private ExtentReport() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_ hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		if(ReadPropertyFile.get("OverrideResults").equalsIgnoreCase("yes")) 
		{
			report=new ExtentReports(".\\ExtentReports\\Test Report.html");
		}
		else 
		{
			report=new ExtentReports(".\\ExtentReports\\Test Report_"+currentDate+".html");
		}
		report.loadConfig(new File("./src/test/resources/extentreport.xml"));
	}

	public static void initialize()
	{
		ExtentReport report=new ExtentReport();
	}

}

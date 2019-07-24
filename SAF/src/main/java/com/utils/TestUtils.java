package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.browser.Driver;
import com.listener.ListenerClass;
import com.reports.ExtentReport;
import com.reports.LogStatus;




/*
 * All the utilities needed for the framework is placed in this class including excel utilities, screenshot capture.
 * We have used method overloading concept in getCellContent Method.
 */
public class TestUtils {

	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testCases= new ArrayList<String>();
	public static List<String> runStatus= new ArrayList<String>();
	public static List<String> testDescription= new ArrayList<String>();
	public static List<String> invocationCount= new ArrayList<String>();
	public static List<String> priority= new ArrayList<String>();
	public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();
	public static String screenshotPath=ReadPropertyFile.get("ScreenshotPath");


	/*
	 * Reads the data from the excel sheet and store the values in respective lists which will be used in annotation transformer class
	 */

	public static void getRunStatus() throws Exception {
		try {
			fs=new FileInputStream(ReadPropertyFile.get("TestDataLocation"));
			workbook=new XSSFWorkbook(fs);
			sheet=workbook.getSheet("RunManager");
			for(int i=1;i<=getLastRowNum("RunManager");i++) {
				//rowAndTestCaseMap.put(i,sheet.getRow(i).getCell(0).getStringCellValue().toString());
				testCases.add(getCellContent("RunManager", i, "TestCaseName"));
				testDescription.add(getCellContent("RunManager", i, "Test Case Description"));
				runStatus.add(getCellContent("RunManager", i, "Execute"));
				invocationCount.add(getCellContent("RunManager", i, "InvocationCount"));
				priority.add(getCellContent("RunManager", i, "Priority"));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static Object getRowNumForTestCase(String testcasename) { Object
	 * a=null; for(Map.Entry m:rowAndTestCaseMap.entrySet()){
	 * if(m.getValue().toString().equalsIgnoreCase(testcasename)) { a= m.getKey(); }
	 * } return a; }
	 */

	/*
	 * Takes rowname and sheetname as parameter
	 * return row number based of rowname
	 */
	public static int getRowNumForRowName(String sheetname,String rowName) {
		int rownum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum=i;
				break;
			}
		}

		return rownum;
	}

	/*
	 * Takes columnname and sheetname as parameter
	 * return column number based of columnheader
	 */

	public static int getColumnNumForColumnName(String sheetname, String columnname) {
		int colnum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=0;i<getLastColumnNum(sheetname, 0);i++) {
			if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum=i;
				break;
			}
		}

		return colnum;

	}


	/*
	 * Takes sheetname as parameter
	 * return last row number of the sheet
	 */
	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	/*
	 * Takes sheetname, row number as parameter
	 * return last cell number of the row
	 */
	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}


	/*
	 * Takes sheetname, row number, column number as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,int colnum) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().toString();

	}

	/*
	 * Takes sheetname, row number, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,String columnname) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(getColumnNumForColumnName(sheetname, columnname)).getStringCellValue().toString();

	}

	/*
	 * Takes sheetname, row name, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,String rowname,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int rownum=getRowNumForRowName(sheetname, rowname);
		System.out.println(rownum);
		int colnum=getColumnNumForColumnName(sheetname, columnname);
		System.out.println(colnum);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().toString();

	}



	//takes screenshot
	public static void takeScreenshot()  {

		if(ReadPropertyFile.get("ScreenshotsRequired").equalsIgnoreCase("yes")) {

			File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
			try {
				if(screenshotPath.equals("")) {
					FileUtils.copyFile(scrFile, new File("./screenshots/" + ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png"));
				}
				else
				{
					FileUtils.copyFile(scrFile, new File(screenshotPath+"/screenshots/" + ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png"));	
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public static String pullScreenshotPath() {
		
		String destination=null;
		if(ReadPropertyFile.get("ScreenshotsRequired").equalsIgnoreCase("yes")) {
			File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
			try 
			{
				if(screenshotPath.equals("")) {

					destination=System.getProperty("user.dir")+"/screenshots/" +ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png";
					FileUtils.copyFile(scrFile, new File(destination));
				}
				else {
					destination=screenshotPath+"/screenshots/" +ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png";
					FileUtils.copyFile(scrFile, new File(destination));
				}
			} 

			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return destination;
	}


}

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

public class TestUtils {

	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testcases= new ArrayList<String>();
	public static List<String> runstatus= new ArrayList<String>();
	public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();

	public static void getRunStatus() throws Exception {
		try {
			fs=new FileInputStream(ReadPropertyFile.get("TestDataLocation"));
			workbook=new XSSFWorkbook(fs);
			sheet=workbook.getSheet("RunManager");
			for(int i=1;i<=getLastRowNum("RunManager");i++) {
				rowAndTestCaseMap.put(i,sheet.getRow(i).getCell(0).getStringCellValue().toString());
				if(sheet.getRow(i).getCell(2).getStringCellValue().equalsIgnoreCase("No")) {
					testcases.add(sheet.getRow(i).getCell(0).getStringCellValue().toString());
				}

			}
			for(int i=0;i<testcases.size();i++) {

				System.out.println(testcases.get(i));
			}
		}
		catch(FileNotFoundException e) {

		}

	}

	public static Object getRowNumForTestCase(String testcasename) {
		Object a=null;
		for(Map.Entry m:rowAndTestCaseMap.entrySet()){    
			if(m.getValue().toString().equalsIgnoreCase(testcasename)) {
				a= m.getKey();
			}
		}
		return a;
	}
	
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


	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}
	
	public static String getCellContent(String sheetname,int rownum,int colnum) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().toString();
		
	}
	public static String getCellContent(String sheetname,int rownum,String columnname) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(getColumnNumForColumnName(sheetname, columnname)).getStringCellValue().toString();
		
	}
	
	public static String getCellContent(String sheetname,String rowname,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int rownum=getRowNumForRowName(sheetname, rowname);
		System.out.println(rownum);
		int colnum=getColumnNumForColumnName(sheetname, columnname);
		System.out.println(colnum);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().toString();
		
	}


	public static void click(WebElement element)  {
		highlightElement(element);
		element.click();
		TestUtils.takeScreenshot();
	}



	public static void sendkeys(WebElement element, String text) {
		highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass(text + " is entered in to the "+ element);
		ExtentReport.logger.addScreenCapture(pullScreenshotPath());
	}

	public static void highlightElement(WebElement element) {
		((JavascriptExecutor)Driver.driver).executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static boolean isVisible(WebElement element) {
		boolean flag=false;
		Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try
		{
			if(element.isDisplayed())
			{
				flag=true;
			}
		}
		catch (Exception e) {

		}
		return flag;
	}


	public static void takeScreenshot()  {

		if(ReadPropertyFile.get("ScreenshotsRequired").equalsIgnoreCase("yes")) {

			File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
			try {

				FileUtils.copyFile(scrFile, new File("./screenshots/" + ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png"));
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public static String pullScreenshotPath() {
		String destination=null;
		File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
		try {
			destination=System.getProperty("user.dir")+"/screenshots/" +ListenerClass.TestcaseName+"/"+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(scrFile, new File(destination));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination;

	}


}

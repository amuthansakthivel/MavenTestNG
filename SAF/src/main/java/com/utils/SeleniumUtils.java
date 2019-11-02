package com.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.browser.Driver;
import com.browser.DriverManager;
import com.reports.ExtentReport;
import com.reports.LogStatus;

public class SeleniumUtils {

	
	public static void click(WebElement element)  {
		highlightElement(element);
		element.click();
		LogStatus.pass("Clicking is successfull on "+ element);
		LogStatus.pass("Screenshot below", TestUtils.pullScreenshotPath());
	}



	public static void sendkeys(WebElement element, String text)  {
		highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass(text + " is entered in to the "+ element);
		LogStatus.pass(text + " is entered in to the "+ element, TestUtils.pullScreenshotPath());
		
	}

	public static void highlightElement(WebElement element) {
		((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static boolean isVisible(WebElement element) {
		boolean flag=false;
		DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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

	
	
	
}

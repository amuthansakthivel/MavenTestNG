package com.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.browser.Driver;
import com.reports.ExtentReport;
import com.reports.LogStatus;

public class SeleniumUtils {

	
	public static void click(WebElement element)  {
		highlightElement(element);
		element.click();
		TestUtils.takeScreenshot();
	}



	public static void sendkeys(WebElement element, String text) {
		highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass(text + " is entered in to the "+ element);
		ExtentReport.logger.addScreenCapture(TestUtils.pullScreenshotPath());
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

	
	
	
}

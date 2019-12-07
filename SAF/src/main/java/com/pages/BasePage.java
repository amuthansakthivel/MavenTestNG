package com.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.browser.Driver;
import com.browser.DriverManager;
import com.constants.Constants;
import com.reports.LogStatus;
import com.utils.TestUtils;

public class BasePage {
	
	protected BasePage(){
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	private static void explicitlyWait(WebElement element) {
		WebDriverWait wait=new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	

	public static void click(WebElement element)  {
		explicitlyWait(element);
		highlightElement(element);
		element.click();
		LogStatus.pass("Clicking is successfull on "+ element);
		LogStatus.pass("Screenshot below", TestUtils.pullScreenshotPath());

	}

	public static void click(By by)  {
		click(DriverManager.getDriver().findElement(by));
	}


	public static void sendkeys(WebElement element, String text)  {
		explicitlyWait(element);
		highlightElement(element);
		element.sendKeys(text);
		LogStatus.pass(text + " is entered in to the "+ element);
		LogStatus.pass(text + " is entered in to the "+ element, TestUtils.pullScreenshotPath());
		
	}
	
	public static void sendkeys(By by, String text)  {
		sendkeys(DriverManager.getDriver().findElement(by), text);
	}
	
	
	public static void moveToElement(WebElement element) {
		Actions actions= new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}
	
	public static void moveToElement(By by) {
		moveToElement(DriverManager.getDriver().findElement(by));
	}

	private static void highlightElement(WebElement element) {
		((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
	}

	
	public static void switchToNewWindow() {
		String parentWinHandle = DriverManager.getDriver().getWindowHandle();
		Set<String> winHandles = DriverManager.getDriver().getWindowHandles();
		for(String temp:winHandles) {
			if(!temp.equalsIgnoreCase(parentWinHandle)) {
				DriverManager.getDriver().switchTo().window(temp);
				LogStatus.pass("Switched to new Window : " +temp);
			}
		}
	}
	
	public static void selectByValue(WebElement element,String text) {
		new Select(element).selectByValue(text);
		LogStatus.pass("Selected dropdown " +element + "with value " +text);
	}
	
	public static void selectByVisibleText(WebElement element,String text) {
		new Select(element).selectByVisibleText(text);
		LogStatus.pass("Selected dropdown " +element + "with text " +text);
	}
	
	public static void selectByIndex(WebElement element,int index) {
		new Select(element).selectByIndex(index);
		LogStatus.pass("Selected dropdown " +element + "with index " +index);
	}
	
	

}

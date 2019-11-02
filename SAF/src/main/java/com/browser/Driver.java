package com.browser;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.reports.LogStatus;
import com.utils.EventCapture;
import com.utils.ReadPropertyFile;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * 
 * @author asakthiv
 * Driver class is used to start browsers based on the property file input.
 * User gets the option to work on firefox and chrome browser.
 * Private constructor to avoid external initialisation	 - SingletonPattern is achieved
 */

public class Driver {
	
	

	public  WebDriver driver=null;

	private Driver() 
	{
		String browser=ReadPropertyFile.get("Browser");
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) 
			{
				WebDriverManager.firefoxdriver().setup();
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logs.txt");
				driver= new FirefoxDriver();
			}
		}
		catch (Exception e) {
			LogStatus.fail(e);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ReadPropertyFile.get("WaitTime")), TimeUnit.SECONDS);
		EventHandlerInit();
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().deleteAllCookies();
		DriverManager.setWebDriver(driver);
	}

	public static void initialize() {
		if(DriverManager.getDriver()==null)
		new Driver();
	}

	public static void quit() {
		if(DriverManager.getDriver()!=null) {
			DriverManager.getDriver().quit();
		}
	}


	/*
	 * Used to listen to driver events
	 */

	private void EventHandlerInit() {
		EventFiringWebDriver eventhandle = new EventFiringWebDriver(driver);
		EventCapture capture= new EventCapture();
		eventhandle.register(capture);
		driver=eventhandle;
	}



}

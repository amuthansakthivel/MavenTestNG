package com.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.constants.Constants;
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
	public DesiredCapabilities capability;

	private Driver() throws MalformedURLException 
	{
		if(ReadPropertyFile.get("RunMode").equalsIgnoreCase("local")) {
			startBrowserForLocal();
		}
		else if(ReadPropertyFile.get("RunMode").equalsIgnoreCase("Remote"))
		{
			startBrowserForRemote();
		}
		else {
			try {
				throw new Exception("Please set up the run mode properly in TestRunDetails.properties");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ReadPropertyFile.get("WaitTime")), TimeUnit.SECONDS);
		EventHandlerInit();
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().deleteAllCookies();
		DriverManager.setWebDriver(driver);
	}

	private void startBrowserForLocal() {
		String browser=ReadPropertyFile.get("Browser");
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().setup();  //WebDriverManager some time wont work because of proxy issues
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) 
			{
				//WebDriverManager.firefoxdriver().setup(); //WebDriverManager some time wont work because of proxy issues
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVERPATH);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logs.txt");
				driver= new FirefoxDriver();
			}
		}
		catch (Exception e) {
			LogStatus.fail(e);
		}
	}
	
	private void startBrowserForRemote() throws MalformedURLException {
		String browser=ReadPropertyFile.get("Browser");
		switch(browser){

		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			driver=new RemoteWebDriver(new URL(ReadPropertyFile.get("RemoteURL")),capability);
			break;
		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			driver=new RemoteWebDriver(new URL(ReadPropertyFile.get("RemoteURL")),capability);
			break;
		default:
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			driver=new RemoteWebDriver(new URL(ReadPropertyFile.get("RemoteURL")),capability);
			break;
		}
	}

	public static void initialize()  {
		if(DriverManager.getDriver()==null)
			try {
			new Driver();
			}
		catch(Exception e) {
			
		}
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

package com.browser;

import java.beans.EventHandler;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.reports.LogStatus;
import com.utils.EventCapture;
import com.utils.ReadPropertyFile;




public class Driver {

	public static WebDriver driver=null;

	private Driver() {
		String browser=ReadPropertyFile.get("Browser");
		
		
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/chromedriver.exe");
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities capabilities= new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver=new ChromeDriver();
			
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				FirefoxOptions FFoptions= new FirefoxOptions();
				FFoptions.addArguments("--incognito");
				driver= new FirefoxDriver(FFoptions);
				
			}
		}
		catch (Exception e) {
			LogStatus.fail(e);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		EventHandlerInit();
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().deleteAllCookies();
	}
	
	public static void initialize() {
		new Driver();
	}
	
	public static void quit() {
		if(driver!=null) {
			driver.quit();
		}
	}

	private void EventHandlerInit() {
		EventFiringWebDriver eventhandle = new EventFiringWebDriver(driver);
		EventCapture capture= new EventCapture();
		eventhandle.register(capture);
		driver=eventhandle;
		
		
	}



}

package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.browser.Driver;

public class HomePageTests {
	
	@BeforeMethod
	public void setUp() {
		Driver.initialize();
	}
	
	@AfterMethod
	public void wrapUp() {
		Driver.driver.close();
	}

	

	@Test
	public void test3() {
		//test case 3
		Assert.assertEquals(1, 1);
	}
}

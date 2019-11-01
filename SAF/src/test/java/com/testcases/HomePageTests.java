package com.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.browser.Driver;
import com.pages.HomePage;
import com.pages.LoginPage;

public class HomePageTests extends BaseTest{
	
	LoginPage loginpage;
	HomePage homepage;
	
	
	

	@Test
	public void test3(Hashtable<String,String> data) {
		homepage=new HomePage();
		homepage.searchOnGoogle(data.get("valueforsearch"));
		Assert.assertEquals(1, 1);
	}
}

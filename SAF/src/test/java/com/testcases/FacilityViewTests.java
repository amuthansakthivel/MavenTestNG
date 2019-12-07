package com.testcases;

import java.util.Hashtable;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.HomePage;
import com.pages.LoginPage;

public class FacilityViewTests extends BaseTest{
	
	LoginPage loginpage;
	HomePage homepage;
	
	
	
	
	
	@Test
	public void test1(Hashtable<String,String> data) {
		homepage=new HomePage();
		homepage.searchOnGoogle(data.get("valueforsearch"));
		
		//Assert.assertEquals(1, 2);
		
	}
	@Test
	public void test2(Hashtable<String,String> data) {
		homepage=new HomePage();
		homepage.searchOnGoogle(data.get("valueforsearch"));
		//Assert.assertEquals(1, 2);
		
	}
	
	
	
	
	
	

}

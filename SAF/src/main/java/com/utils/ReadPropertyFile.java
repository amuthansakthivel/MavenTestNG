package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {

	/*
	 * 
	 */
	public static String get(String propertyname) {
		
		String returnproperty=null;
		Properties property = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/TestRunDetails.properties");
			property.load(file);
			returnproperty = property.getProperty(propertyname);
			if(returnproperty==null) {
				throw new Exception("Property named "+propertyname+ "not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnproperty;
		
	}
	
}

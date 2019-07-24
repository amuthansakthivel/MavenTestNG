package com.utils;

import org.openqa.selenium.By;


/*
 * It will be used to handle dynamic xpaths at run time
 */
public class DynamicXpath {

	public static By get(String xpath, String data) {
		String rawxpath = xpath.replaceAll("%replacable%", data);
		return By.xpath(rawxpath);
		
	}
}

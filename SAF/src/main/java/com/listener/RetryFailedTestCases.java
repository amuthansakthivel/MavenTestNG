package com.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.utils.ReadPropertyFile;



/*
 * Logic to retry test cases and no of retries depends on parameter from properties file
 */
public class RetryFailedTestCases implements IRetryAnalyzer{

	int counter=0;
	int limit=Integer.parseInt(ReadPropertyFile.get("NoOfRetries"));

	@Override
	public boolean retry(ITestResult result) {
		if(counter<limit) {
			counter++;
			return true;
		}
		return false;
	}

}

package com.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.utils.TestUtils;

public class AnnotationTransformer implements IAnnotationTransformer{
	int count=0;

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(RetryFailedTestCases.class);
		try {
			if(count==0) {
				TestUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<TestUtils.testcases.size();i++) {
			if(testMethod.getName().equalsIgnoreCase(TestUtils.testcases.get(i)))
			{
				annotation.setEnabled(false); 
				break;
			} 
			
		}
		
		count++;
	}
}




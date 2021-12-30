package com.crm.autodesk.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListnersImplementation implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// capture the method name
		String methodName = result.getMethod().getMethodName();
		
		//capture the current date
		String CurrentDate = new JavaUtility().getSystemDateWithFormat();
		
		//concatinate for screenshot name
		String screenShotName = methodName+"_"+CurrentDate;
		
		try {
			new WebdriverUtility().takeScreenshot(BaseClass.sdriver, screenShotName);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	

}

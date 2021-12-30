package practice;

import org.junit.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserDemo {
	
	@Test(retryAnalyzer = com.crm.autodesk.genericUtility.RetryAnalyserImpl.class)
	
	public void retryAnalyserDemoTest() {
		
		System.out.println("RetryAnalyser");
		Assert.fail();
	}

}

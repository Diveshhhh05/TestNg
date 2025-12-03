package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	private int retryCount = 0;
	final private static  int maxRetry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetry) {
			retryCount++;
			return true;
		}
		return false; //No more retries
	}
	

}

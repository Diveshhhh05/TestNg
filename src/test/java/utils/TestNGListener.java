package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseTest;

public class TestNGListener extends BaseTest implements ITestListener {
	
	ExtentReports extent;
	
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Test Execution Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Divesh Masurkar");
		extent.setSystemInfo("Enviornment", "QA");
	}
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed Successfully");
	}
	public void onTestFailure(ITestResult result) {
		
		Object currentClass = result.getInstance();
		WebDriver driver = ((BaseTest) currentClass).driver;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")  + "/screenshots/" + result.getName() + ".png";
		try {
			FileUtils.copyFile(src, new File(screenshotPath));
			extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failed Test Screenshot");
			System.out.println("Scrrenshot saved for failed test: "+result.getName());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

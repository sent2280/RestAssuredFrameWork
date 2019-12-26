package utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Listners extends TestListenerAdapter {
	
	public ExtentHtmlReporter reporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		System.out.println("Inside On start..");
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Report.html");
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Rest API Testing Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("user", "kumar");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Inside OnTestSuccess..");
		test = extent.createTest(result.getName());
		test.log(Status.PASS, " Test cases " + result.getName() + " is PASSED");
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Inside on Test Failure..");
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, " Test cases " + result.getName() + " is FAILED");
		test.log(Status.FAIL, " Test cases FAILED is " + result.getThrowable());
		
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Inside on Test skipped..");
	}
	
	public void onFinish(ITestContext testContext) {
		System.out.println("Inside on Test Finished..");
		extent.flush();
	}
}

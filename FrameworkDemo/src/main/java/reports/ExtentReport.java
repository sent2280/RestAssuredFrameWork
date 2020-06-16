package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void createReport() {
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/output/ExtentReport/TestExecutionReport.html");
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Rest API Testing Report");
		System.out.println("Ext Report file is create successfully");
	}
	
	public void configureReport() {	
		System.out.println("Inside configure report file..");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("user", "kumar");		
	}
	
	public void logTestOnSuccess(String testName) {
		System.out.println("Inside on Test Success..");
		test = extent.createTest(testName);
		test.log(Status.PASS, " Test cases " + testName + " is PASSED");
	}
	
	public void logTestOnFailure(String testName, Throwable exception) {
		System.out.println("Inside on Test Failure..");
		test = extent.createTest(testName);
		test.log(Status.FAIL, " Test cases " + testName + " is FAILED");
		test.log(Status.FAIL, " Test cases FAILED is " + exception);	
	}

	public void logTestOnSkip(String testName) {
		test = extent.createTest(testName);
		test.log(Status.SKIP, " Test cases " + testName + " is Skipped");	
	}
	
	public void flushToReport() {
		extent.flush();	
	}
}

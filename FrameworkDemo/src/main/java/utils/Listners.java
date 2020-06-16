package utils;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import reports.ExtentReport;

public class Listners extends TestListenerAdapter {
	
	public JSONObject jsonObject;
	public JSONArray jsonArray;
	public FileUtil fileUtil; 
	public FileWriter file;
	private long suiteStartTime;	
	private long suiteEndTime;
	private ExtentReport extentReport;
	
	public void onStart(ITestContext testContext) {
		
		extentReport = new ExtentReport();
		extentReport.createReport();
		extentReport.configureReport();
	
		String suiteName = testContext.getName();
		jsonObject = new JSONObject();
		jsonArray = new JSONArray();
		fileUtil = new FileUtil();
		file = fileUtil.createFile(file,"\\output\\result.json");
		suiteStartTime = testContext.getStartDate().getTime(); 
		jsonObject.put("Test suite name", suiteName);
		jsonObject.put("Test start time", formatDate(testContext.getStartDate()));
	}
	
	public void onTestSuccess(ITestResult result) {
		logJsonFileOnSuccess(result);
		extentReport.logTestOnSuccess(result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		logJsonFileOnFailure(result);
		extentReport.logTestOnFailure(result.getName(), result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		extentReport.logTestOnSkip(result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		suiteEndTime = testContext.getEndDate().getTime();
		jsonObject.put("Test end time", formatDate(testContext.getEndDate()));
		jsonObject.put("Total duration" , totalDuration(suiteStartTime,suiteEndTime) + " Secs");
		jsonObject.put("Test cases", jsonArray);
		fileUtil.flushToFile(file,jsonObject.toJSONString());
		fileUtil.closeFile(file);
		System.out.println("Inside on Test Finished..");
		extentReport.flushToReport();
	}
	
	private void logJsonFileOnSuccess(ITestResult result) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Description", result.getMethod().getDescription());
		jsonObject.put("Start time", formatDate(new Date(result.getStartMillis())));
		jsonObject.put("End Time", formatDate(new Date(result.getEndMillis())));
		jsonObject.put("Test Case name", result.getName());
		jsonObject.put("Test result", "PASS");
		jsonArray.add(jsonObject);
	}
	
	private void logJsonFileOnFailure(ITestResult result) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Description", result.getMethod().getDescription());
		jsonObject.put("Start time", formatDate(new Date(result.getStartMillis())));
		jsonObject.put("End Time", formatDate(new Date(result.getEndMillis())));
		jsonObject.put("Test Case name", result.getName());
		jsonObject.put("Test result", "FAIL");
		jsonObject.put("Error msg", result.getThrowable().getLocalizedMessage());
		jsonArray.add(jsonObject);
	}
	
	private String formatDate(Date date) {
		SimpleDateFormat datePattern = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return datePattern.format(date);
	}
	
	private long totalDuration(long suiteStartTime, long suiteEndTime) {
		 long time = 0;
		 return time = (suiteEndTime - suiteStartTime) / (1000);
	}
	
}




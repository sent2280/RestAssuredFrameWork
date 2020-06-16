package reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.codemodel.JArray;

import utils.JSONProcesser;

public class HTMLSummaryReportGenerator {
	
	public void createSummaryReport() {
		
		System.out.println("Creating summary report...");
		
		 String summaryTemplateHeader ="<html>"
		 		+ "<head>"
			    + "</head>"
		 		+ "<body>"
			    + "<div>"
				+ "<h1>Employee Details Execution Report</h1>"
				+ "<h2>Summary:</h2>"
				+ "</div>";
		 
		String tableHeader =  "<table width='75%' border='1'>"
								 + "<tr>"
								 + "<th>" + "Test case name" + "</th>"
								 + "<th>" + "Short description" + "</th>" 
								 + "<th>" + "Test result" + "</th>"
								 + "<th>" + "Start time" + "</th>"
								 + "<th>" + "End time" + "</th>"
								 +"</tr>";
		
				 
		 String summaryTemplateFooter = "</body>"
		 								+ "</html>";
		 
		 JSONProcesser jsonProcesser = new JSONProcesser("./output/result.json");
		 JSONArray jArray = jsonProcesser.getJsonArrayFromResult("Test cases");
		 
		 JSONObject jObject = jsonProcesser.getJsonObject();
		 
		 String testSuiteName = (String) jObject.get("Test suite name");
		 String testStartTime = (String) jObject.get("Test start time");
		 String testEndTime = (String) jObject.get("Test end time");
		 String totalDuration = (String) jObject.get("Total duration");
		// String Description = (String) jObject.get("Description");
		 int totalTestCases = jArray.size();
		 int totalPass = getPassCount(jArray);
		 int totalFail = getFailCount(jArray);
		 
		 String summaryInfoTemplate = "<p>"
				 					+ "Test Suite name : " + testSuiteName + "<br>"
				 				//	+ "Test start time : " + testStartTime + "<br>"
				 				//	+ "Test Start time : " + testStartTime + "<br>"
				 					+ "Total Duration : " + totalDuration + "<br>"
				 					+ "Total Test cases : " + totalTestCases + "<br>"
				 					+ "Total Pass : " + totalPass + "<br>"
				 					+ "Total Fail : " + totalFail +  "<br>"
				 					+ "</p>";
		 
		 String summaryTemplateBody = frameTemplateBody(jArray);
				
		 File file = new File(System.getProperty("user.dir") + "//output//" + "EmailableReport.html");
		 
		 try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(summaryTemplateHeader + summaryInfoTemplate + tableHeader + summaryTemplateBody + summaryTemplateFooter);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String frameTemplateBody(JSONArray jArray) {
		
		System.out.println("Inside frameTemplateBody ... ");
		String summaryTemplateBody = "";
		
		for(Object obj:jArray) 
		{
			
			JSONObject jObj = (JSONObject)obj;
			
			summaryTemplateBody = summaryTemplateBody + 
								 "<tr>"
								   + "<td>" + jObj.get("Test Case name") + "</td>"
								  + "<td>" + jObj.get("Description") + "</td>"
								   + "<td>" + jObj.get("Test result") + "</td>"
								   + "<td>" + jObj.get("Start time") + "</td>"
								   + "<td>" + jObj.get("End Time") + "</td>"
								   +"</tr>";
		}
		System.out.println("summaryTemplateBody = " + summaryTemplateBody);
		return summaryTemplateBody;
	}
	
private int getPassCount(JSONArray jArray) {
		
		String summaryTemplateBody = "";
		int passCount=0;
		for(Object obj:jArray) 
		{
			JSONObject jObj = (JSONObject)obj;
			String result = (String) jObj.get("Test result");
			
			if(result.equalsIgnoreCase("PASS")) {
				++passCount;
			}		
		}
		return passCount;
	}

private int getFailCount(JSONArray jArray) {
	
	String summaryTemplateBody = "";
	int failCount=0;
	for(Object obj:jArray) 
	{
		JSONObject jObj = (JSONObject)obj;
		String result = (String) jObj.get("Test result");
		
		if(result.equalsIgnoreCase("FAIL")) {
			++failCount;
		}		
	}
	return failCount;
}

}

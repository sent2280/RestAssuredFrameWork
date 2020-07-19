package reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.codemodel.JArray;

import utils.JSONProcesser;

public class HTMLDetailReportGenerator {
	
	public void createDetailReport() throws IOException {
		
		//BufferedReader bufferedReader = new BufferedReader(new FileReader("./../resources/StyleSheet.txt"));
	
		System.out.println("Inside HTML Detail Report Generation....");
		
		StyleSheet StyleSheet = new StyleSheet();
		String styleTemplate = StyleSheet.getStyleTemplate();
		
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
		
		/* String styleSheetPath = new File("src/main/java/reports/StyleSheet.txt").getAbsolutePath();
		
		//InputStream in = getClass().getResourceAsStream("StyleSheet.txt");
		InputStream in = getClass().getResourceAsStream(styleSheetPath);
		System.out.println("styleSheetPath = " + styleSheetPath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		System.out.println("bufferedReader = " + bufferedReader.readLine());
		//BufferedReader bufferedReader = new BufferedReader(new FileReader(styleSheetPath)); 
		
		
			
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(" line = " + line);
			stringBuilder.append(line);
		}
		
		String styleTemplate = stringBuilder.toString();    */
		
		System.out.println("styleTemplate = " + styleTemplate);
		
		 String summaryTemplateHeader ="<html>"
		 		+ "<head>"
				+ styleTemplate
			    + "</head>"
		 		+ "<body>"
			    + "<div>"
				+ "<h1>" + testSuiteName + " Execution Report</h1>"
				+ "<h2>Summary:</h2>"
				+ "</div>";
		 
		String tableHeader =  "<table width='75%' border='1'"
								 + "<tr class=\"headerRow\">"
								 + "<th>" + "Sl.No" + "</th>"
								 + "<th>" + "Short description" + "</th>" 
								 + "<th>" + "Execution Status" + "</th>"
								 + "<th>" + "Start Time" + "</th>"
								 + "<th>" + "End Time" + "</th>"
								 +"</tr>";
		
				 
		 String summaryTemplateFooter = "</body>"
		 								+ "</html>";
		 
		 
		 String summaryInfoTemplate = "<p>"
				 					+ "Test Suite name : " + testSuiteName + "<br>"
				 					+ "Total Duration : " + totalDuration + "<br>"
				 					+ "Total Test cases : " + totalTestCases + "<br>"
				 					+ "Total Pass : " + totalPass + "<br>"
				 					+ "Total Fail : " + totalFail +  "<br>"
				 					+ "</p>";
		 
		 String summaryTemplateBody = frameTemplateBody(jArray);
				
		 File file = new File(System.getProperty("user.dir") + "//output//" + "TestExecutionReport.html");
		 
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
		
		String summaryTemplateBody = "";
		
		int counter = 0;
		for(Object obj:jArray) 
		{
			
			JSONObject jObj = (JSONObject)obj;
			
			String result = (String) jObj.get("Test result");
			
			if(result.equalsIgnoreCase("PASS")) {
				summaryTemplateBody = summaryTemplateBody + 
						// "<tr class=passRow>"
						"<tr style=\"background-color: lightgreen\">"
						   + "<td>" + ++counter + "</td>"
						  + "<td>" + jObj.get("Description") + "</td>"
						   + "<td>" + jObj.get("Test result") + "</td>"
						   + "<td>" + jObj.get("Start time") + "</td>"
						   + "<td>" + jObj.get("End Time") + "</td>"
						   +"</tr>";	
				
			}
			else {
				JSONObject jObjFail = (JSONObject)obj;
				
					summaryTemplateBody = summaryTemplateBody + 
							    "<tr style=\"background-color: lightcoral\">"
							   + "<td>" + ++counter + "</td>"
							   + "<td>" + jObjFail.get("Description") + "</td>"
							   + "<td>" + jObjFail.get("Test result") + "</td>"
							   + "<td>" + jObjFail.get("Start time") + "</td>"
							   + "<td>" + jObjFail.get("End Time") + "</td>"
							   +"</tr>";	
			}
			
			
		}
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

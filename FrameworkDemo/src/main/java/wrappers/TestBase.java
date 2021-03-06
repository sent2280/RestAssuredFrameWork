package wrappers;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import hooks.hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import reports.ExtentReport;
import reports.HTMLDetailReportGenerator;
import reports.HTMLSummaryReportGenerator;
import utils.FileUtil;
import utils.JSONProcesser;

public class TestBase extends hooks
{
	
	public static RequestSpecification setLogs() {
		//RestAssured.authentication = RestAssured.basic(Constants.AUTH_USERNAME, Constants.AUTH_PASSWORD);
		RestAssured.useRelaxedHTTPSValidation();
		RequestLogSpecification log = RestAssured.given().log();
		//return log.all().contentType(getContentType());
		return log.all();
	}

	public static Response get(String URL) {
		return setLogs()
					.when()
					.get(URL);
	}

	public static Response getRequestWithPathParams(Map<String, String> parametersMap,Map<String, String> headers,String URL) {
		return setLogs().params(parametersMap)
						.when()
						.headers(headers)
						.get(URL);
	}
	
	public static Response getRequestWithParamsAndURL(Map<String, String>parametersMap,String URL) {
		return setLogs().params(parametersMap)
						.when()
						.get(URL);
	}

	public static Response getRequestWithOnlyParams(Map<String, String>parametersMap) {
		return setLogs().params(parametersMap)
						.when()
						.get();
	}
	public static Response getWithHeader(Map<String, String> headers, String URL) {

		return setLogs()
					.when()
					.headers(headers)
					.get(URL);
	}

	
	//using this method currently
	public static Response postWithHeaderAndForm(Map<String, String> headers, String jsonObject, String URL) {

		return setLogs()
					.headers(headers)
					.body(jsonObject)
					.post(URL);
	}

	public static Response postWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
						.body(jsonObject)
						.post(URL);
	}

	public static Response postWithHeaderAndJsonBody(Map<String, String> headers, String jsonObject, String URL) {

		return setLogs()
					.when()
					.headers(headers)
					.body(jsonObject)
					.post(URL);
	}

	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {

		return setLogs()
					.when()
					.headers(headers)
					.post(URL);
	}

	public static Response delete(String URL) {
		return setLogs()
					.when()
					.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		return setLogs()
					.when()
					.headers(headers)
					.body(jsonObject)
					.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(Map<String, String> headers, String URL)
			throws Exception {
		return setLogs()
					.when()
					.headers(headers)
					.delete(URL);
	}
	
	public static Response putWithHeaderAndBodyForm(Map<String, String> headers, String jsonObject, String URL) {

		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.put(URL);
	}

	public static Response putWithHeaderAndBodyParam(Map<String, String> headers, JSONObject jsonObject, String URL) {

		return RestAssured.given()
							.headers(headers)
		//					.contentType(getContentType())
							.request().body(jsonObject).when()
							.put(URL);
	}

	
	private static ContentType getContentType() {
		//return ContentType.fromContentType(Constants.JSON_CONTENTTYPE);
		return ContentType.JSON;

	} 
	
	public void storeJsonToOutputFolder(ITestResult result,ITestContext ctx) {
		
		FileWriter file = null;
		String methodName = result.getMethod().getMethodName();
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		
		String filePath = "./output/JsonOutput/" + suiteName;
		File fileObj = new File(filePath);
		
		if(!fileObj.exists()) {
			System.out.println("Ouput json directory not exist.. Creating new directory...");
			fileObj.mkdir();
		}else {
			System.out.println("Ouput json directory already exists");
		}
		
		if(response.getContentType().contains("application/json")) {
			FileUtil fileUtilObj = new FileUtil();
			file = fileUtilObj.createFile(file, filePath + "/" + methodName + ".json");
			fileUtilObj.flushToFile(file, response.getBody().asString());
			fileUtilObj.closeFile(file);
		}
		
	}

	/*
	public static void verifyContentType(Response response, String type){
		if (response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportRequest("The Content type " + type + " matches the expected content type", "PASS");
		} else {
			reportRequest("The Content type " + type + " does not match the expected content type "
					+ response.getContentType(), "FAIL");
		}
	}

	public static void verifyResponseCode(Response response, String responseCode){
		if (response.statusCode() == Integer.parseInt(responseCode)) {
			reportRequest("The status code " + responseCode + " matches the expected code", "PASS");
		} else {
			reportRequest(
					"The status code " + response.statusCode() + " does not match the expected code " + responseCode,
					"FAIL");

		}
	}

	public static void verifyResponseStatusLineCode(Response response, String code){
		if (response.statusLine() == code) {
			reportRequest("The status code " + code + " matches the expected code", "PASS");
		} else {
			reportRequest("The status code " + code + " does not match the expected code" + response.statusLine(),
					"FAIL");

		}
	}

	public static void verifyResponseTime(Response response, long time) {
		if (response.time() <= time) {
			reportRequest("The time taken " + response.time() + " with in the expected time", "PASS");
		} else {
			reportRequest("The time taken " + response.time() + " is greater than expected SLA time " + time,
					"WARNING");
		}
	}

	public static void verifyContentWithKey(Response response,String responsekey, String expVal){

		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(responsekey);
			//System.out.println("actValue :" +actValue.length() + expVal.length() );
			if ((expVal.length() == 0 && actValue == null) ||
					(actValue != null && actValue.equalsIgnoreCase(expVal.trim()))) {
				reportRequest("The JSON response has value " + expVal + " as expected. ", "PASS");
			}
			else {
				reportRequest(
						"The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ",
						"FAIL");
			}
		}
	}

	public static void verifyContentsWithKey(Response response, String key, String expVal){
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if (actValue != null && actValue.get(0).equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value " + expVal + " as expected. ", "PASS");
			} else {
				reportRequest(
						"The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ",
						"FAIL");
			}
		}
	}

	public static List<String> getContentsWithKey(Response response, String key){
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);
		} else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void verifyMongoDocumentValue(String mongoCollectionName, String query, String key,
			String expectedValue) throws Exception {
		try{
		List<HashMap> result = MongoDBWrapper.getRecords(mongoCollectionName, query);
		Object keyvalue = result.get(0).get(key);
		String actualValue = String.valueOf(keyvalue);
		if (expectedValue.equals(actualValue)) {
			reportRequest("The mongodb response has value " + actualValue + " as expected. ", "PASS");
		} else {
			reportRequest("The mongodb response :" + actualValue + " does not have the value " + expectedValue
					+ " as expected. ", "FAIL");
		}
		}catch(Exception e){
		// BigDecimal tranid = new BigDecimal(String.valueOf(keyvalue));
			reportRequest("Exception occured while validating Mongo database value", "FAIL");
		}
	}
	

	public static void dropMongoCollection(String mongoCollectionName, String query) {
		MongoDBWrapper.dropCollection(mongoCollectionName);
	}

	public void insertOneDocumentIntoMongoCollection(String mongoCollectionName, String jsonString) {
		try {
			MongoDBWrapper.InsertOneDocument(mongoCollectionName, jsonString);
			reportRequest("Document inserted successfully", "PASS");
		} catch (Exception e) {
			reportRequest("Exception occured while inserting single Document", "PASS");
			e.printStackTrace();
		}
	}

	public static void insertMultiDocumentIntoMongoCollection(String mongoCollectionName, ArrayList<String> jsonList) {
		try {
			MongoDBWrapper.InsertMultiDocuments(mongoCollectionName, jsonList);
			reportRequest("Multiple Document inserted successfully", "PASS");
		} catch (Exception e) {
			reportRequest("Exception occured while inserting Multiple Document", "PASS");
			e.printStackTrace();
		}
	}   

	@SuppressWarnings("rawtypes")
	public static void verifySQLDBContentValue(String query, String key, String expectedValue) throws Exception {
		List<HashMap> result;
		result = SQLWrapper.getResultSet(query);
		Object keyvalue = result.get(0).get(key);
		String actualValue = String.valueOf(keyvalue);
		if (expectedValue.equals(actualValue)) {
			reportRequest("The sqldb response has value " + actualValue + " as expected. ", "PASS");
		} else {
			reportRequest("The sqldb response :" + actualValue + " does not have the value " + expectedValue
					+ " as expected. ", "FAIL");
		}
	}

	public static void updataMySqlDataIntoDb(String query, String indicatorKey) {
		// indicatorKey--is( update or delete or insert)
		try {
			SQLWrapper.modifyDataIntoDB(query, indicatorKey);
			reportRequest(indicatorKey + " records affected ", "PASS");
		} catch (Exception e) {
			reportRequest("Exception occured while" + indicatorKey + "ing" + "records", "FAIL");
			e.printStackTrace();
		}
	}   */
	
	/*
	public void logResponseInReport(Response response) {
		reportRequest(response.prettyPrint(), "Info");
	}  */

}

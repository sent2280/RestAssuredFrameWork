package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import hooks.hooks;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import wrappers.TestBase;


public class TestCase2_GetSingleEmpDetails extends TestBase {
	
	
	@Test(description = "Get single employee details")
	public void getSingleEmployee() throws InterruptedException {
	
		logger.info("TestCase2 Get Single employee details started...");
		
		//Dummy request for cookie generation
		
		Map<String,String> responseCookie = RestAssured.given().get("employee/1").cookies();
		
	   /* response = RestAssured.given().cookies(responseCookie).get("employees");
	    response.prettyPrint();
		String empID = response.jsonPath().get("data[0].id");
		System.out.println("empID " + empID); */
		
	    response = RestAssured.given().cookies(responseCookie).log().all().get("employee/" + empID);
	    response.prettyPrint();
	    
		logger.info("Validating statusCode ...");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		logger.info("Validating response time ...");
		long responseTime  = response.getTime();
		Assert.assertTrue(responseTime < 10000);
			
		logger.info("Validating content type...");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
		logger.info("Validating server type...");
		String server = response.header("Server");
		Assert.assertEquals(server,"nginx/1.16.0");
		
		logger.info("Validating content Length...");
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength) > 50);
		
		logger.info("Validating employee ID...");
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("data.id"), empID);
		
		logger.info("TestCase2 Get Single employee details Completed ...");
		
	}
}

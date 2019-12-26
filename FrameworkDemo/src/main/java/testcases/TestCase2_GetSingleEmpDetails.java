package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import wrappers.TestBase;

public class TestCase2_GetSingleEmpDetails extends TestBase {
	
	
	@Test()
	public void getSingleEmployee() throws InterruptedException {
	
		logger.info("TestCase2 Get Single employee details started...");
		RestAssured.baseURI = baseURIGlobal;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/" + empID);
		
		
		logger.info("Validating statusCode ...");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		logger.info("Validating response time ...");
		long responseTime  = response.getTime();
		Assert.assertTrue(responseTime < 10000);
			
		logger.info("Validating content type...");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType,"text/html; charset=UTF-8");
		
		logger.info("Validating server type...");
		String server = response.header("Server");
		Assert.assertEquals(server,"nginx/1.16.0");
		
		logger.info("Validating content Length...");
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength) > 50);
		
		logger.info("Validating employee ID...");
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("id"), empID);
		
		logger.info("TestCase2 Get Single employee details Completed ...");
		
	}
}

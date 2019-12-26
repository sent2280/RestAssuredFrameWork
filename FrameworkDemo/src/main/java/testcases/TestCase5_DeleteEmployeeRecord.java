package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import utils.RandomValueGenerator;
import wrappers.TestBase;

public class TestCase5_DeleteEmployeeRecord extends TestBase {
	
	 private String empName = RandomValueGenerator.getEmpName();
	 private String salary = RandomValueGenerator.getSalary();
	 private String age = RandomValueGenerator.getAge();
	
	@BeforeMethod()
	public void getEmpID() {
		logger.info("TestCase5: delete employee record pre-condition started...");
		RestAssured.baseURI = baseURIGlobal;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		JsonPath jsonPath = response.jsonPath();
		empID = jsonPath.get("[0].id");
		logger.info("emp id processing is " + empID);
	}
	
	@Test()
	public void deleteEmployee() throws InterruptedException {
	
		logger.info("TestCase5: employee record deletion started...");
		// httpRequest = RestAssured.given();
		
		
		response = httpRequest.request(Method.DELETE,"/delete/" + empID);
		
		logger.info("printing response body" + response.getBody().asString());
		
		
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
		
		
		logger.info("Validating response body ...");
		JsonPath jsonPath = response.jsonPath();	
		Assert.assertNotNull(jsonPath.get("success.text"));
				
		logger.info("TestCase5: employee record deleted Sucessfully ...");
		
	}
}

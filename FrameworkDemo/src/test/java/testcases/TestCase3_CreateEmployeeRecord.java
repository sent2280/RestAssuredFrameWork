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

public class TestCase3_CreateEmployeeRecord extends TestBase {
	
	 private String empName = RandomValueGenerator.getEmpName();
	 private String salary = RandomValueGenerator.getSalary();
	 private String age = RandomValueGenerator.getAge();
	
	
	@Test(description = "Create employee")
	public void createEmployee() throws InterruptedException {
	
		logger.info("TestCase3: Create employee started...");
		
		httpRequest = RestAssured.given();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", empName);
		jsonObject.put("salary", salary);
		jsonObject.put("age", age);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jsonObject.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		
		logger.info(response.asString());
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
		
		
		logger.info("Validating emp age ...");
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get("data.age"), age);
		
		logger.info("TestCase3: Create employee Completed ...");
		
	}
}

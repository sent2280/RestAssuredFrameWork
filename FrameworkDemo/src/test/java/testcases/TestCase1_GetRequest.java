package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import wrappers.TestBase;

public class TestCase1_GetRequest extends TestBase {
	
	
	@Test(description = "Get all the employee details and verify status code, "
			+ "response time etc")
	public void getAllEmployee() throws InterruptedException {
		
		logger.info("Started Testcase1 getAllEmployee details...");
		
		httpRequest	= RestAssured.given().log().headers();
		response = httpRequest.request(Method.GET,"employees");
		
		Thread.sleep(5000);
				
		logger.info("Checking response body..");
		String responseBody = response.getBody().asString();
		//logger.info("response body" + responseBody);
		Assert.assertTrue(responseBody != null);
		
		logger.info("Checking status code ..");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		logger.info("Checking response time..");
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 10000);
		
		logger.info("Checking Content-Type..");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
		logger.info("Testcase1 getAllEmployee details... Completed");
		
	}
}

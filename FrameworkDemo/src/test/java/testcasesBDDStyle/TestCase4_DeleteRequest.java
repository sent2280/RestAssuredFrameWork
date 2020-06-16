package testcasesBDDStyle;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import hooks.hooks;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase4_DeleteRequest extends hooks {
	
	@Test
	public void deleteRequest() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/delete/";
		RestAssured.basePath = empID;
		
		String response = given().
		when().
			  delete().
		then().	
			  statusCode(200).
			  statusLine("HTTP/1.1 200 OK").
			  contentType("text/html; charset=UTF-8").
			  log().all().
			  extract().asString();
		
		Assert.assertTrue(response.contains("successfully! deleted Records"));
		
		Response responseObj = given().
				when().
					  delete().
				then().	
					  statusCode(200).
					  statusLine("HTTP/1.1 200 OK").
					  contentType("text/html; charset=UTF-8").
					  log().all().
					  extract().response();
				
				Assert.assertEquals(responseObj.jsonPath().get("success.text"), "successfully! deleted Records");
		
	}
	

}

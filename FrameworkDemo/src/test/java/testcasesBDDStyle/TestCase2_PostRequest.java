package testcasesBDDStyle;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import utils.RandomValueGenerator;

public class TestCase2_PostRequest {
	
	public static HashMap map = new HashMap();
	
	
	@BeforeMethod
	public void dataSetup() {
		
		map.put("FirstName", RandomValueGenerator.getFirstName());
		map.put("LastName", RandomValueGenerator.getLastName());
		map.put("UserName", RandomValueGenerator.getUserName());
		map.put("Password", RandomValueGenerator.getPassword());
		map.put("Email", RandomValueGenerator.getEmail());
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
	}
	
	
	@Test
	public void registerUser() {
		
		given().
			   contentType("application/json").
			   body(map).
	    when().
	    	   post().
	    then().
	    	   statusCode(201).
	   		   body("SuccessCode",equalTo("OPERATION_SUCCESS")).
			   body("Message",equalTo("Operation completed successfully"));
	}
	

}

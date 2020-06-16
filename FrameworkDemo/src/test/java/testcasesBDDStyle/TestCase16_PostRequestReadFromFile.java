package testcasesBDDStyle;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import utils.RandomValueGenerator;

public class TestCase16_PostRequestReadFromFile {
		
	File fileObj;
	
	@BeforeMethod
	public void dataSetup() {
		
		String filePath = System.getProperty("user.dir");
		fileObj = new File(filePath + "//src//main//java//resources//employee.json");
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
	}
	
	
	@Test
	public void registerUser() {
		
		given().
			   contentType("application/json").
			   body(fileObj).
	    when().
	    	   post().
	    then().
	    	   log().body().
	    	   statusCode(201).
	   		   body("SuccessCode",equalTo("OPERATION_SUCCESS")).
			   body("Message",equalTo("Operation completed successfully"));
			   
	}
	

}

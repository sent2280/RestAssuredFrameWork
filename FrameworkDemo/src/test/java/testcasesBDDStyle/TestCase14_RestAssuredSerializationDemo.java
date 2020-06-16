package testcasesBDDStyle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.RandomValueGenerator;

public class TestCase14_RestAssuredSerializationDemo {
	
public static HashMap map = new HashMap();
	
	
	//@BeforeMethod
	public void dataSetup() {
		
		map.put("FirstName", RandomValueGenerator.getFirstName());
		map.put("LastName", RandomValueGenerator.getLastName());
		map.put("UserName", RandomValueGenerator.getUserName());
		map.put("Password", RandomValueGenerator.getPassword());
		map.put("Email", RandomValueGenerator.getEmail());
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
	}
	
    // Normal approach - post request
	
	//@Test
	public void registerUser() {
		
		dataSetup();
		
		given().
			   contentType(ContentType.JSON).
			   body(map).
			   log().everything().
	    when().
	    	   post().
	    then().
	    	   statusCode(201).
	   		   body("SuccessCode",equalTo("OPERATION_SUCCESS")).
			   body("Message",equalTo("Operation completed successfully"))
			   .log().ifError();
	}
	
	@Test
	public void registerUserUsingSerilization() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
		
		EmployeeRecord1 employeeRecord = new EmployeeRecord1();
		employeeRecord.setfirstName();
		employeeRecord.setLastName();
		employeeRecord.setUserName();
		employeeRecord.setpassword();
		employeeRecord.setemail();
		
		given()
			.contentType(ContentType.JSON)
			.body(employeeRecord)
			.log().everything()
		.when()
			.post()
		.then()
		    .statusCode(201).
 		    body("SuccessCode",equalTo("OPERATION_SUCCESS")).
		    body("Message",equalTo("Operation completed successfully"));
	}
	
	
}

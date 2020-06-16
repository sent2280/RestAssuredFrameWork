package testcasesBDDStyle;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Then;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import hooks.hooks;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase5_JsonResponseValidation extends hooks {
	
	// Single parameter validation
	// Since return type is HTML (Other than Json), So passing json path directly to body will not work
	// We can convert this as json path using extract method
	
	//@Test
	public void jsonValidation() {
	
		given()
		.when()
			.get("http://dummy.restapiexample.com/api/v1/employees")
		.then()
			.statusCode(200)
			.body("[0].id" , equalTo("1"))
			.log().all();
	}
	
	
	//@Test
	public void jsonValidationTestwhenReturnContentTypeIsNotJson() {
	
		 Response response = 
			when()
				.get("http://dummy.restapiexample.com/api/v1/employees")
			.then()
				.statusCode(200)
				.log().all()
			    .extract().response();
		 
		 JsonPath jsonpath =   response.jsonPath();
		 logger.info("" + jsonpath.get(jsonpath.get("[0].id")));
		 Assert.assertEquals(jsonpath.get("[0].id"), "1");
		 
	}
	
	// Optimized approach
	
	//@Test
		public void jsonValidationTestwhenReturnContentTypeIsNotJson_InSingleStep() {
		
			when()
				.get("http://dummy.restapiexample.com/api/v1/employees")
			.then()
				.statusCode(200)
				.log().body()
				.extract().jsonPath().get("data[0].id").equals("1");
		}
		
		
}

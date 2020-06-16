package testcasesBDDStyle;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase12_RequestAndResponseSpecification {
	
	public RequestSpecBuilder requestSpecBuilder;
	public ResponseSpecBuilder responseSpecBuilder;
	public RequestSpecification requestSpecification;
	public ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void setup()
	{
		 requestSpecBuilder	 = new RequestSpecBuilder();
		 requestSpecBuilder.addParam("myparam", "paramValue");
		 requestSpecBuilder.addHeader("myheader", "myheaderValue1");
		 requestSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com/");
		 requestSpecBuilder.setContentType(ContentType.JSON);
		 requestSpecification = requestSpecBuilder.build();
		 
		 responseSpecBuilder = new ResponseSpecBuilder();
		 responseSpecBuilder.expectStatusCode(200);
		 responseSpecBuilder.expectContentType("application/json; charset=utf-8");
		 responseSpecBuilder.expectHeader("Content-Encoding", "gzip");
		 responseSpecification = responseSpecBuilder.build();
	}
	
	@Test(priority=1)
	public void testalbums() {
		
		given()
			.spec(requestSpecification)
		.when()
			.get("users/1/albums")
		.then()
			.spec(responseSpecification)
			.log().all();
		
	}
	
	@Test(priority=2)
	public void testTodos() {
		
		given()
			.spec(requestSpecification)
		.when()
			.get("users/1/todos")
		.then()
			.spec(responseSpecification)
			.log().all();
		
	}
	
	@Test(priority=3)
	public void testPosts() {
		
		given()
			.spec(requestSpecification)
		.when()
			.get("users/1/posts")
		.then()
			.spec(responseSpecification)
			.log().all();
		
	}

}

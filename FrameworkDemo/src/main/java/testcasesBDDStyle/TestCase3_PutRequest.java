package testcasesBDDStyle;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import utils.RandomValueGenerator;
import wrappers.TestBase;

public class TestCase3_PutRequest extends TestBase{
	
	public static HashMap map = new HashMap();

	@BeforeClass
	public void dataSetup() {
		
		map.put("name", RandomValueGenerator.getEmpName());
		map.put("salary", RandomValueGenerator.getSalary());
		map.put("age", RandomValueGenerator.getAge());
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		RestAssured.basePath = "update/" + empID;
	}
	
	@Test
	public void postRequest() {
		
		given().
				contentType("application/json").
				body(map).
		when().
			    put().
		then().
				statusCode(200).log().all();
	}
}

package testcasesBDDStyle;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase1_GETRequest {
	
	@Test
	public void getWeather() {
		
		given()
		.when()
			.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("City", equalTo("Hyderabad"))
			.header("Content-Type","application/json");
		
	}
	
	// Here Json path returns only one value but expected are two values
	// So test case will fail
	
	@Test
	public void getWeatherReportMultipleValidation() {
			
		when()
			.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("City", hasItems("Hyderabad","newyork"))
			.header("Content-Type","application/json");
		
	}
	
}

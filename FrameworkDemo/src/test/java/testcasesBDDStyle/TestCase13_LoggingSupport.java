package testcasesBDDStyle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class TestCase13_LoggingSupport {
	
	// We can Log only headers, body, cookies or all the response. Conditional base Logging also possible.
	// For Ex. If something fails.
	
	@Test
	public void getWeather() {
		
		given()
		.when()
			.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("City", equalTo("Hyderabad"))
			.header("Content-Type","application/json")
			.log().body()
			.log().headers()
			.log().cookies()
			.log().ifStatusCodeIsEqualTo(200)
			.log().ifError()
			.log().all();
		
	}

}

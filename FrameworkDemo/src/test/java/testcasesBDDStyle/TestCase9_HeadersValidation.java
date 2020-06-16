package testcasesBDDStyle;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase9_HeadersValidation {
	
	@Test
	public void directHeaderValidation() {
		
		when()
			.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.header("Server", "nginx")
			.header("Content-Encoding", "gzip");
	}
	
	@Test
	public void extractSingleHeaderValue() {
		
		
			String contentEncodingHeader = 
					get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").getHeader("Content-Encoding");
			System.out.println("contentEncodingHeader = " + contentEncodingHeader);
			Assert.assertEquals(contentEncodingHeader, "gzip");
			
	}
	
	@Test
	public void extractallHeaderValue() {
			
			Headers headers = 
					get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").getHeaders();
			
			for (Header header : headers) {		
				System.out.println("header name = " + header.getName() + " header Value = " + header.getValue());			
			}
	}

}

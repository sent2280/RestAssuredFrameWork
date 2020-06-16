package testcasesBDDStyle;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase8_extraction {

	//Extracting single node value
	
	@Test
	public void singleNodeValueExtraction() {
		
		String url =
				when()
					.get("http://jsonplaceholder.typicode.com/photos/2")
				.then()
					.statusCode(200)
					.extract().path("url");

		//Sending request to the retrieved URL
		
		when()
			.get(url)
		 .then()
		 	.statusCode(200);
		
	}
	
	//Extracting single node value - simplified approach
	
		@Test
		public void singleNodeValueExtractionSimplifiedApproach() {
			
			String url = when().get("http://jsonplaceholder.typicode.com/photos/2").path("url");
			System.out.println("url " + url);
			
		}
		
		//Extracting response object
		
			@Test
			public void responseExtraction() {
				
		   Response response = when().
								get("http://jsonplaceholder.typicode.com/photos/2")
						   .then()
								.statusCode(200)
								.extract().response();
						
						
				System.out.println("getStatusCode = " + response.getStatusCode());
				System.out.println("getTime = " + response.getTime());
				System.out.println("contentType = " + response.contentType());
				
			}
			
			//Extracting response object - simplified approach
			
			@Test
			public void responseExtractionSimplifiedApproach() {
				
		   Response response = when().
								get("http://jsonplaceholder.typicode.com/photos/2").andReturn();
						
						
						
				System.out.println("getStatusCode = " + response.getStatusCode());
				System.out.println("getTime = " + response.getTime());
				System.out.println("contentType = " + response.contentType());
				
			}
		
	
}

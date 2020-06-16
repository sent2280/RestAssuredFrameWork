package testcasesBDDStyle;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase6_XMLResponseValidation {
	
	//Response validation using XML path
	//One-way of writing xpath
	
	@Test
	public void XMLResponseValidationWay1() {
		
		when()
		   .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15/")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.body("CUSTOMER.ID", equalTo("15"))
			.body("CUSTOMER.FIRSTNAME", equalTo("Bill"))
			.body("CUSTOMER.LASTNAME", equalTo("Clancy"))
			.body("CUSTOMER.STREET", equalTo("319 Upland Pl."))
			.body("CUSTOMER.CITY", equalTo("Seattle"));
	}
	
	
	// Response validation using XML path
	// other way of writing xpath
		
	@Test
		public void XMLResponseValidationWay2() {
			
			when()
			   .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15/")
			.then()
				.statusCode(200)
				.contentType("application/xml")
				.body(hasXPath("/CUSTOMER/ID", containsString("15")))
				.body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Bill")))
				.body(hasXPath("/CUSTOMER/LASTNAME", equalTo("Clancy")))
				.body(hasXPath("/CUSTOMER/STREET", equalTo("319 Upland Pl.")))
				.body(hasXPath("/CUSTOMER/CITY", equalTo("Seattle")));
		}
	
	    
		// Response validation using XML path
		// Usage of text() method in xpath
			
		@Test
			public void XMLResponseValidaationTextMethodUsage() {
				
				when()
				   .get("http://www.thomas-bayer.com/sqlrest/INVOICE/")
				.then()
					.statusCode(200)
					.contentType("application/xml")
					.body(hasXPath("/INVOICEList/INVOICE[text()='35']"));
			}

}

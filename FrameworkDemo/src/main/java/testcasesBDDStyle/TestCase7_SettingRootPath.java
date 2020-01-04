package testcasesBDDStyle;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class TestCase7_SettingRootPath {
	
	// Instead of repeating json/xml path, if some part of the path is common then we can set, this as root Path
	// detaching the path also possible
	
	
	// XML response validation without setting root path	
		@Test
		public void XMLResponseValidationWithoutRootPath() {
			
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
		
		
		// XML response validation with setting root path	
				
				@Test
				public void XMLResponseValidationWithRootPath() {
					
					when()
					   .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15/")
					.then()
						.statusCode(200)
						.contentType("application/xml")
						.rootPath("CUSTOMER")
						.body("ID", equalTo("15"))
						.body("FIRSTNAME", equalTo("Bill"))
						.body("LASTNAME", equalTo("Clancy"))
						.detachRoot("CUSTOMER")
						.body("CUSTOMER.STREET", equalTo("319 Upland Pl."))
						.body("CUSTOMER.CITY", equalTo("Seattle"));
				}

}

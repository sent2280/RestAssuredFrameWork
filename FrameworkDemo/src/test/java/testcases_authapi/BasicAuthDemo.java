package testcases_authapi;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthDemo {
	
	@Test
	public void basicAuthTest() {
		
		given()
			.auth().basic("ToolsQA", "TestPassword")
		.when()
			.get("http://restapi.demoqa.com/authentication/CheckForAuthentication/")
		.then()
			.statusCode(200)
			.body("FaultId",is("OPERATION_SUCCESS"))
			.body("Fault",is("Operation completed successfully"))
			.body("'Authentication Type'",is("Basic"))                     // When space comes in between json key, it has to be escaped
			.log().all();											 	   // with single quotes surrounding it
		
	}

}

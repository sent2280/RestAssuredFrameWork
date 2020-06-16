package testcasesBDDStyle;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase11_QueryAndPathParam {
	
	@Test
	public void setQueryAndPathParam() {
		
		// End point - http://api.plos.org/search?q=title:DNA
		
		given()
			  .pathParam("searchPath", "search")
			  .queryParam("q", "title:DNA")
		.when()
			   .get("http://api.plos.org/{searchPath}")
	    .then()
	    		.statusCode(200)
	    		.body("response.numFound",equalTo(5094));
	}

}

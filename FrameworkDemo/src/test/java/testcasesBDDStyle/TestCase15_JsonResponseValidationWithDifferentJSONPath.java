package testcasesBDDStyle;

import static io.restassured.RestAssured.get;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.groovy.control.customizers.builder.PostCompletionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class TestCase15_JsonResponseValidationWithDifferentJSONPath {

	//@Test
	public void jsonValidationLastNode() {
	
		JsonPath jsonPath1 = get("http://dummy.restapiexample.com/api/v1/employees")
							.then()
								.log().body()
								.extract().jsonPath();
			
			Assert.assertEquals(jsonPath1.get("data[-1].id"), "24");
			Assert.assertEquals(jsonPath1.get("data[-3].id"), "22");
			
			List<HashMap> list = jsonPath1.get("data.findAll{ data -> data.id == '22' }");
			System.out.println("list[0] = " + list.get(0));
			//Assert.assertEquals(jsonPath1.get("data.findAll{data.id=='22'}"), new ArrayList(22));
			
	}
	
	// JSON simple is not supporting relative jsonPath
	
	@Test
	public void jsonValidationLastNode1() throws IOException, ParseException {
	
			String filePath = System.getProperty("user.dir");
			File filePathObj = new File(filePath + "//src//main//java//resources//glossary.json");
			JsonPath jsonPath = JsonPath.with(filePathObj);
			
			System.out.println("title = " + jsonPath.get("$..title"));
			System.out.println("Full path = " + jsonPath.get("glossary.GlossDiv.GlossList.GlossEntry.ID"));
			
	}
}

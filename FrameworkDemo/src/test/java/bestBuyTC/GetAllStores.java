package bestBuyTC;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import wrappers.TestBase;

public class GetAllStores extends TestBase {
	
	@Test(description = "Get all the stores of bestbuy")
	public void getAllStores() {
		
		RestAssured.baseURI = "https://api.bestbuy.com/v1";
		
		HashMap<String, String> paramsMap = new HashMap();
		paramsMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		paramsMap.put("format", "json");
		response = getRequestWithParamsAndURL(paramsMap,"stores");
		
		int totalStores = response.jsonPath().get("total");
		System.out.println("totalStores = " + totalStores);
		
	}

}

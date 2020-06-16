package testcasesBDDStyle;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class TestCase10_WorkingWithCookies {
	
	@Test(priority=1)
	public void validateCookie() {
		
		when()
			.get("http://jsonplaceholder.typicode.com/photos/2")
		.then()
			.cookie("__cfduid",not(equalTo("dee2c4c4b3cb07d2b96d45c1b238484201578124714")));
	}

	@Test(priority=2)
	public void getSingleAndMultipleCookies() {
		
			String cookieValue = get("http://jsonplaceholder.typicode.com/photos/2").getCookie("__cfduid");
			System.out.println("cookieValue = " + cookieValue);
			
			Map<String, String> map = get("http://jsonplaceholder.typicode.com/photos/2").getCookies();
			
			for (Map.Entry entry : map.entrySet()) {
				System.out.println("Key = " + entry.getKey() + "Value = " + entry.getValue());
			}
	}
	
	@Test(priority = 3)
	public void getDetailedCookieInfo() {
		
			Cookie cookies = get("http://jsonplaceholder.typicode.com/photos/2").getDetailedCookie("__cfduid");
			
			System.out.println("Expiry Date = " + cookies.getExpiryDate());
			System.out.println("Domain = " + cookies.getDomain());
			System.out.println("Path = " + cookies.getPath());
			System.out.println("Has expiry date = " + cookies.hasExpiryDate());
			System.out.println("Has domain = " + cookies.hasDomain());
			System.out.println("Has Path = " + cookies.hasPath());
			
	}
}

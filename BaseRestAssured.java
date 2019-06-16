package day2;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRestAssured {
	
	public static String sys_id;
	public static RequestSpecification rSpec;
	
	@BeforeSuite
	public void init() {

		// End Point
		RestAssured.baseURI =
				"https://dev81252.service-now.com"
				+ "/api/now/table/change_request";

		// Set up Authentication
		RestAssured.authentication = 
				RestAssured.basic("admin", 
						"Lw4bN4WAkdRe");
		
		
		rSpec = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.XML)
				.accept(ContentType.XML);

	}

}

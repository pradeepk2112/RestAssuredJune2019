package day1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetAllChange {
	
	@Test
	public void getAllIncidents() {
		
		// End Point
		RestAssured.baseURI ="https://dev81252.service-now.com/api/now/table/change_request";
		
		// Set up Authentication
		RestAssured.authentication = 
				RestAssured.basic("admin", "Lw4bN4WAkdRe");
		
		Map<String,String> parametersMap = new HashMap<String, String>();
		parametersMap.put("type","normal");
		parametersMap.put("category","network");
		
		// Send the request
		Response response = RestAssured
				.given()
				.header(new Header("accept", "application/xml"))
				.params(parametersMap)
				.get();
		
		// Check the response code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		// Parse the response
		String contentType = response.getContentType();
		System.out.println(contentType);
		
		// Response - XML
		XmlPath xml = response.xmlPath();
		
		// Get the incident
		List<String> list = xml.getList("response.result.sys_id");
		System.out.println(list.size());
		for (String each : list) {
			System.out.println(each);
		}
		
	}

}

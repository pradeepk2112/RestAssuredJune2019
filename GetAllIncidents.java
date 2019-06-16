package day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAllIncidents {
	
	@Test
	public void getAllIncidents() {
		
		// End Point
		RestAssured.baseURI ="https://dev81252.service-now.com/api/now/table/";
		
		// Set up Authentication
		RestAssured.authentication = 
				RestAssured.basic("admin", "Lw4bN4WAkdRe");
		
		
		
		// Map
		Map<String,String> parametersMap = new HashMap<String, String>();
		parametersMap.put("urgency", "1");
		parametersMap.put("severity", "3");

		// Send the request
		Response response = RestAssured
				.given()
				.log().all()
				.queryParams(parametersMap)
				.pathParam("type", "incident")
				.get("{type}");
		
		// Get the response -> 
		response.prettyPrint();
		
		// Check the response code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		// Parse the response
		JsonPath jsonPath = response.jsonPath();
		
		// Read your expected data
		List<String> list = jsonPath.getList("result.number");
		System.out.println(list.size());
		for (String eachNumber : list) {
			System.out.println(eachNumber);
		}
		System.out.println("***********************************");
		list = jsonPath.getList("result.urgency");
		System.out.println(list.size());
		for (String eachNumber : list) {
			System.out.println(eachNumber);
		}
		System.out.println("***********************************");
		list = jsonPath.getList("result.severity");
		System.out.println(list.size());
		for (String eachNumber : list) {
			System.out.println(eachNumber);
		}
		
	}

}

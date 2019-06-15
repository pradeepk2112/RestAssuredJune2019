package day1;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MultipleData {
	
	@Test(dataProvider="multiple")
	public void getAllIncidents(String fileName) {
		
		// End Point
		RestAssured.baseURI ="https://dev81252.service-now.com/api/now/table/incident";
		
		// Set up Authentication
		RestAssured.authentication = 
				RestAssured.basic("admin", "Lw4bN4WAkdRe");
		
		File data = new File(fileName);		
		
		Response response = RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(data)
			.post();
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
		
	}

	
	@DataProvider(name="multiple",parallel=true)
	public Object[][] getData(){
		
		String[][] fileNames = new String[2][1];
		fileNames[0][0] = "data.json";
		fileNames[1][0] = "data1.json";
		return fileNames;
		
	}
}

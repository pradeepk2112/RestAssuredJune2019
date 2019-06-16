package day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetAllChange extends BaseRestAssured {
	
	@Test
	public void getAllChanges() {
		
		Map<String,String> parametersMap = new HashMap<String, String>();
		parametersMap.put("type","normal");
		parametersMap.put("category","network");
		
		// Send the request
		Response response = rSpec				
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
		
		// Get the first change
		sys_id = (String) xml.getList("response.result.sys_id").get(0);
		System.out.println(sys_id);
		
	}

}

package day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteChange extends BaseRestAssured {
	
	@Test(dependsOnMethods="day2.GetAllChange.getAllChanges")
	public void deleteIncident() {
		
		// Delete the incident
		Response response = rSpec.delete("/"+sys_id);
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		
	}

}

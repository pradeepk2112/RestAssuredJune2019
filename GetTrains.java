package day2;

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

public class GetTrains {
	
	@Test(dataProvider="multiple")
	public void getAllTrains(String source, String dest) {
		
		// End Point parameterized
		RestAssured.baseURI ="https://api.railwayapi.com/v2/between/source/"
								+source+"/dest/"
								+dest+"/date/16-06-2019/apikey/3fmfgnvu44/";
		
		// No Authentication needed
		
		Response response = RestAssured
			.given()
			.contentType(ContentType.JSON)
			.get();
		
		response.prettyPrint();
		
		List<String> trainList = response.jsonPath().getList("trains.number");
		for (String eachTrain : trainList) {
			System.out.println(eachTrain);
		}
		
	}

	
	@DataProvider(name="multiple",parallel=true)
	public Object[][] getData(){
		
		String[][] trains = new String[2][2];
		trains[0][0] = "SBC";
		trains[0][1] = "MAS";
		
		trains[1][0] = "MAS";
		trains[1][1] = "SBC";
		return trains;
		
	}
}

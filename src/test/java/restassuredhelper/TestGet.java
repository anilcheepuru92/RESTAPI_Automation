package restassuredhelper;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestGet extends BaseClass{
	//@Test
	public void testGet()
	{
		/* 
		 * Given the condition is set to Accept the response in Json/XML format
		 * When I perform the get request
		 */
		
		try {
			URI	uri = new URI("http://localhost:9090/laptop-bag/webapi/api/all");
			Response response = RestAssured.given().
					accept(ContentType.JSON).
					when().
					get(uri);
			System.out.println(response.asString());
			//System.out.println(RestAssured.when().get(uri).asString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetStatusCode()
	{
		/* 
		 * Given the condition is set to Accept the response in Json/XML format
		 * When I perform the get request
		 * Then verify the status code to be 200
		 */
		
		try {
			URI	uri = new URI("http://localhost:9090/laptop-bag/webapi/api/all");
			/*int statusCode = RestAssured.given().
					accept(ContentType.JSON).
					when().
					get(uri).thenReturn().statusCode();
			System.out.println(statusCode);*/
			RestAssured.given().
			accept(ContentType.JSON).
			when().
			get(uri).then().assertThat().statusCode(HttpStatus.SC_OK);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetWithId()
	{
		/* 
		 * Given the condition is set to Accept the response in Json/XML format
		 * When I perform the get request with any existing Id
		 * Then verify the status code to be 200
		 */
		
		try {
			URI	uri = new URI("http://localhost:9090/laptop-bag/webapi/api/find/200");
			/*int statusCode = RestAssured.given().
					accept(ContentType.JSON).
					when().
					get(uri).thenReturn().statusCode();
			System.out.println(statusCode);*/
			System.out.println(baseURI+port+basePath);
			String body = RestAssured.given().
			accept(ContentType.JSON).
			when().
			get(new URI("/find/200")).	//then().assertThat().statusCode(HttpStatus.SC_OK);
			thenReturn().
			body().asString();
			System.out.println(body);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetWithIdWithHeaders()
	{
		/* 
		 * Given the condition is set to Accept the response in Json/XML format
		 * When I perform the get request with any existing Id and with customized headers
		 * Then verify the status code to be 200
		 */
		
		try {
			URI	uri = new URI("http://localhost:9090/laptop-bag/webapi/api/find/200");
	
			Map<String, String> headers = new HashMap();
			headers.put("Accept", "application/xml");
			System.out.println(baseURI+port+basePath);
			String body = RestAssured.given().
			headers(headers).
			when().
			get(new URI("/find/200")).	//then().assertThat().statusCode(HttpStatus.SC_OK);
			thenReturn().
			body().asString();
			System.out.println(body);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetContent()
	{
		/* 
		 * Given the condition is set to Accept the response in Json format
		 * When I perform the get request with any existing Id and with customized headers
		 * Then verify the Brandname to be Orange
		 */
		
		try {
			URI	uri = new URI("http://localhost:9090/laptop-bag/webapi/api/find/200");
	
			Map<String, String> headers = new HashMap();
			headers.put("Accept", "application/xml");
			System.out.println(baseURI+port+basePath);
			
			RestAssured.given().
			accept(ContentType.JSON).
			when().
			get(new URI("/find/200")).	//then().assertThat().statusCode(HttpStatus.SC_OK);
			then().
			//body("BrandName",equalToIgnoringCase("Orange"),"Id", equalTo(200), "LaptopName", equalToIgnoringCase("Tetra")); //this is to validate the non-nested key value pairs
			body("Features.Feature", hasItem("8GB RAM")); //this is to validate the nested key value pairs
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testXMLContent()
	{
		/* 
		 * Given the condition is set to Accept the response in XML format
		 * When I perform the get request with any existing Id and with customized headers
		 * Then verify the Brandname to be Dell
		 * And the features should have 8GB RAM
		 */
		try {
			given().
			accept(ContentType.XML).
			when().
			get(new URI("/find/111")).
			then().
			assertThat().
			body("Laptop.BrandName", equalTo("Dell"), "Laptop.Id", equalTo("111")).
			and().
			assertThat().
			body("Laptop.Features.Feature", hasItem("8GB RAM"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void testXMLContentXmlPath()
	{
		/* 
		 * Given the condition is set to Accept the response in XML format
		 * When I perform the get request with any existing Id and with customized headers
		 * Then verify the Brandname to be Dell
		 * And the features should have 8GB RAM
		 */
		String s = given()
				   .accept(ContentType.XML)
				   .when()
				   .get("/find/111")
				   .thenReturn()
				   .asString();
		XmlPath xmlPath = new XmlPath(s);
		System.out.println(xmlPath.getInt("Laptop.Id"));
		System.out.println(xmlPath.getString("Laptop.BrandName"));
		System.out.println(xmlPath.getString("Laptop.LaptopName"));
		List<String> featuresList = xmlPath.getList("Laptop.Features.Feature");
		for(String features: featuresList)
		{
			System.out.println(features);
		}
	}
	
	@Test
	public void testXMLContentJsonPath()
	{
		String s = given()
				   .accept(ContentType.JSON)
				   .when()
				   .get("/find/111")
				   .thenReturn()
				   .asString();
		JsonPath jsonPath = new JsonPath(s);
		System.out.println(jsonPath.getInt("Id"));
		System.out.println(jsonPath.getString("BrandName"));
		System.out.println(jsonPath.getString("LaptopName"));
		//System.out.println(jsonPath.getList("Features.Feature"));
		List<String> featuresList = jsonPath.getList("Features.Feature");
		for(String features: featuresList)
		{
			System.out.println(features);
		}
	}
}

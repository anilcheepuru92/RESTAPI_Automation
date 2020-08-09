package restassuredhelper;

import static io.restassured.RestAssured.*;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestQueryParameter extends BaseClass{

	@Test
	public void testQueryParameter()
	{
		/*Given the content is Accepted in JSON format
		 *And ID as 111
		 *And LaptopName as Latitude 
		 *When I perform the GET request
		 *Then Status code 200 OK should be returned
		 *And the response content should have id as 111
		 *And Feature list should contain 1TB Hard Drive
		 */
		String s = given()
		.accept(ContentType.JSON)
		.param("id","111")
		.param("laptopName","Latitude")
		.when()
		.get("/query")
		.thenReturn()
		.asString();
		System.out.println(s);
		
	}
}

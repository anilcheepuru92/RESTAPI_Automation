package restassuredhelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestPost extends BaseClass{
	
	//@Test
	public void testPostRequest()
	{
		/* Given the content is accepted in XML Format
		 * With Content type as JSON
		 * And Body 
		 * When I perform the POST request
		 * Then Status code 200 OK should be returned
		 * And the response should contain the ID
		 */
		String id = (int)(1000*(Math.random()))+"";
		String postJsonBody = "{"+
				 "\"BrandName\": \"Dell\","+
				 "\"Features\": {"+
				  "\"Feature\": [\"8GB RAM\","+
				  "\"1TB Hard Drive\"]"+
				 "},"+
				 "\"Id\":"+ id+","+
				 "\"LaptopName\": \"Latitude\""+
				"}";
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(postJsonBody)
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.body("Laptop.Id", equalTo(id));
	}
	
	//@Test
	public void testPostWithObjectMapping()
	{
		/* Create the mapping class
		 * Create the object of mapping class
		 * initialize the field value present in mapping class
		 * send the object along with post request
		 */
		String id = (int)(1000*(Math.random()))+"";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Surface");
		
		Features features = new Features();
		features.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard disk")));
		bag.setFeature(features);
		/* Given the content is accepted in JSON Format
		 * With Content type as XML
		 * And Body 
		 * When I perform the POST request
		 * Then Status code 200 OK should be returned
		 * And the response should contain the ID
		 */
		given()
				.log()
				.body()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(id));
		
	}
	
	@Test
	public void testPostWithDeserialization()
	{
		/* Create the mapping class
		 * Create the object of mapping class
		 * initialize the field value present in mapping class
		 * send the object along with post request
		 */
		String id = (int)(1000*(Math.random()))+"";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Surface");
		
		Features features = new Features();
		features.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard disk")));
		bag.setFeature(features);
		/* Given the content is accepted in JSON Format
		 * With Content type as XML
		 * And Body 
		 * When I perform the POST request
		 * Then Status code 200 OK should be returned
		 * And the response should contain the ID
		 */
		LaptopBag responseBag = given()
				.log()
				.body()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add")
		.thenReturn()
		.as(LaptopBag.class);
		
		System.out.println(responseBag.getId());
		System.out.println(responseBag.getBrandName());
		System.out.println(responseBag.getLaptopName());
		System.out.println(responseBag.getFeature().getFeature());
	}
}

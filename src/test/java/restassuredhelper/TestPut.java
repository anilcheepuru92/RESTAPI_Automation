package restassuredhelper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TestPut extends BaseClass{

	@Test
	public void testPut()
	{
		/*Given the contect is Accepted in JSON
		 * with Content Type as JSON
		 * And Body
		 * When I perform the PUT request with id
		 * The HTTP status code 200 should be returned
		 * And details should get updated
		 * 
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
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add");
		
		features.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard disk","This is PUT method")));
		bag.setFeature(features);
		
		JsonPath body = given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(bag)
		.when()
		.put("/update")
		/*.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Features.Feature", hasItem("This is PUT method"));*/
		.thenReturn()
		.jsonPath();
		Assert.assertEquals(body.getString("BrandName"), "Microsoft");
	}
}

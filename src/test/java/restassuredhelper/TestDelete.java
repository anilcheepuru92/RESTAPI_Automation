package restassuredhelper;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestDelete extends BaseClass {
	
	@Test
	public void testDelete()
	{
		String id = (int)(1000*(Math.random()))+"";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Surface");
		
		Features features = new Features();
		features.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard disk")));
		bag.setFeature(features);
		
		given()
			//	.log()
			//	.body()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add");
		
		expect()
		.statusCode(HttpStatus.SC_OK)
		.when()
		.delete("/delete/"+id);
		
		expect()
		.statusCode(HttpStatus.SC_NOT_FOUND)
		.when()
		.delete("/delete/1");
		
	}
}

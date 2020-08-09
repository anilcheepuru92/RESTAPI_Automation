package com.api.rest.api.auth;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;

import io.restassured.http.ContentType;
import restassuredhelper.Features;
import restassuredhelper.LaptopBag;
import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestPostWithSecure extends BaseSecureClass{

	@Test
		public void testPostWithObjectMapping()
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
					.log()
					.body()
			.accept(ContentType.XML)
			.with()
			//.auth()
			//.preemptive()
			//.basic("admin", "welcome")
			.contentType(ContentType.XML)
			.body(bag)
			.post("http://localhost:9090/laptop-bag/webapi/secure/add")
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.body("Laptop.Id", equalTo(id));
			
		}
}

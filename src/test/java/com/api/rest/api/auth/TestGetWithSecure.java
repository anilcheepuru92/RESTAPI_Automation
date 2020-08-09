package com.api.rest.api.auth;
import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestGetWithSecure {
	//@Test
	public void testGetWithoutAuth()
	{
		expect()
		//.log()
		//.all()
		.statusCode(HttpStatus.SC_UNAUTHORIZED)
		.when()
		.get("http://localhost:9090/laptop-bag/webapi/secure/all");
		
	}
	
	//@Test
	public void testGetWithAuth()
	{
		/*
		 * 1. Authentication using Header
		 * 2. Authentication using method from RestAssured 
		 * 
		 */
		given()
		.log()
		.all()
		.header("Authorization","Basic YWRtaW46d2VsY29tZQ==")
		.when()
		.get("http://localhost:9090/laptop-bag/webapi/secure/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
		@Test
		public void testBasicAuth()
		{
			given()
			.auth()
			.preemptive()
			.basic("admin", "welcome")
			.log()
			.all()
			.when()
			.get("http://localhost:9090/laptop-bag/webapi/secure/all")
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);
		}
}

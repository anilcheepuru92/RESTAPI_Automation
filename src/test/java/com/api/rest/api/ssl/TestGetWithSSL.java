package com.api.rest.api.ssl;
import static io.restassured.RestAssured.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class TestGetWithSSL extends BaseSSLClass{

	//@Test
	public void testGetSSL()
	{
		String s = 
		given()
		.relaxedHTTPSValidation()
		.accept(ContentType.XML)
		.when()
		.get("https://localhost:9090/laptop-bag/webapi/sslres/all")
		.thenReturn()
		.asString();
		System.out.println(s);
	}
	
	//@Test
	public void testGetWithCertificate()
	{
		String s = 
		given()
		.auth()
		.certificate("E:\\Tutorials\\REST API docs\\Resource\\Resource\\mykey.keystore", "password")
		.log()
		.all()
		.when()
		.get("https://localhost:9090/laptop-bag/webapi/sslres/all")
		.thenReturn()
		.asString();
		System.out.println(s);
	}
	
	@Test
	public void testGetWithCertificateBase()
	{ 
		given()
		//.auth()
		//.certificate("E:\\Tutorials\\REST API docs\\Resource\\Resource\\mykey.keystore", "password")
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.body("$", is(notNullValue()));
	}
}

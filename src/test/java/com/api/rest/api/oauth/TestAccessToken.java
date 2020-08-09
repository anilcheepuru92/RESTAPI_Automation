package com.api.rest.api.oauth;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class TestAccessToken {
	private static final String OAUTH_TOKEN = "14b6769b421139b949af537697703f4491c16ed9";
	private static String OAUTH_TOKEN_FRESH = "";
	
	@BeforeClass
	public static void setUp()
	{
		baseURI = "http://coop.apps.symfonycasts.com";
		
		JsonPath path = 
		given()
		.formParam("client_id", "TestOAuthAnil")
		.formParam("client_secret", "59a44378cacc64d78c0d4faa17c6b8df")
		.formParam("grant_type", "client_credentials")
		.when()
		.post("/token")
		.jsonPath();
		
		OAUTH_TOKEN_FRESH = path.getString("access_token");
	}
	
	//@Test
	public void testPost()
	{
		String s = 
		given()
		.auth()
		.oauth2(OAUTH_TOKEN)
		.when()
		.post("http://coop.apps.symfonycasts.com/api/413/chickens-feed")
		.thenReturn()
		.asString();
		System.out.println(s);
	}
	
	@Test
	public void testPostWithFreshToken()
	{
		given()
		.auth()
		.oauth2(OAUTH_TOKEN_FRESH)
		.when()
		.post("http://coop.apps.symfonycasts.com/api/413/chickens-feed")
		.then()
		.assertThat()
		.body("action", equalToIgnoringCase("chickens-feed"));
	}
}


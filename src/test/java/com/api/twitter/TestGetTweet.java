package com.api.twitter;

import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.api.rest.api.model.TwitterModel;
public class TestGetTweet {
	private final String consumerKey = "fFkN32KvL4Me3jUvhAwdczAX1";
	private final String consumerSecret = "HA5dye1L7ZwJK4bPjtmRx6R9aRuqIL0kYO1zrSHwtNCHk1JrqN";
	private final String accessToken = "1078753939439403008-os5lqnp6haCylw39Ve2EJTp0qODMKa";
	private final String secretToken = "8AkOC0y6o6976GMOaX3hRi78A2ykKpin7edip9Y25hr05";
	
	//@Test
	public void postStatusUpdate() throws URISyntaxException
	{
		/*
		 * https://api.twitter.com/1.1/statuses/update.json?status=hello
		 * 
		 * 
		 */
		URI postURI = new URIBuilder()
							.setScheme("https")
							.setHost("api.twitter.com/")
							.setPath("1.1/statuses/update.json")
							.addParameter("status", "This-is-RestAssured-status")
							.build();
		given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
		.post(postURI)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	//@Test
	public void getFollowers() throws URISyntaxException
	{
		URI getUri = new URIBuilder()
		.setScheme("https")
		.setHost("api.twitter.com/")
		.setPath("1.1/followers/list.json")
		.setParameter("screen_name", "neiltyson")
		.setParameter("count", "2")
		.setParameter("skip_status","true")
		.setParameter("include_user_entities", "false")
		.build();
		
		String s = 
		given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
		.get(getUri)
		.thenReturn()
		.asString();
		System.out.println(s);
	}
	
	@Test
	public void deleteTweet() throws URISyntaxException
	{
		URI postURI = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/update.json")
				.addParameter("status", "This-is-RestAssured-status"+Math.random())
				.build();

		TwitterModel postResponse = 
		given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
		.post(postURI)
		.thenReturn()
		.as(TwitterModel.class);
		System.out.println(postResponse.id_Str);
		
		//https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
		URI deleteURI = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/destroy/"+postResponse.id_Str+".json")
				.addParameter("status", "This-is-RestAssured-status"+Math.random())
				.build();
		
		String deleteResponse = 
		given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
		.post(deleteURI)
		.thenReturn()
		.asString();
		System.out.println(deleteResponse);
	}
}

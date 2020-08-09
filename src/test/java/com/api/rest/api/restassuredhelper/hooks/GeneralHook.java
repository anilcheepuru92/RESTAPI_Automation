package com.api.rest.api.restassuredhelper.hooks;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class GeneralHook {
	
	@Before
	public void setup(Scenario scenario)
	{
		baseURI = "http://localhost";
		port = 9090;
		basePath = "/laptop-bag/webapi/api";
	}
	
	@After
	public void teardown(Scenario scenario)
	{
		
	}
}

package com.api.rest.api.auth;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.*;
import org.junit.BeforeClass;

public class BaseSecureClass {
	@BeforeClass
	public static void setUp()
	{
		baseURI = "http://localhost";
		port = 9090;
		basePath = "/laptop-bag/webapi/api";
		authentication = preemptive().basic("admin","welcome");
	}
}

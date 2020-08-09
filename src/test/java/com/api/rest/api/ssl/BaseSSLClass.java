package com.api.rest.api.ssl;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.*;
import org.junit.BeforeClass;

public class BaseSSLClass {
	@BeforeClass
	public static void setUp()
	{
		baseURI = "http://localhost";
		port = 9090;
		basePath = "/laptop-bag/webapi/sslres";
		authentication = certificate("E:\\Tutorials\\REST API docs\\Resource\\Resource\\mykey.keystore", "password");
	}
}


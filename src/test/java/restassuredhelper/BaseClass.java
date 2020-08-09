package restassuredhelper;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class BaseClass {
	
	@BeforeClass
	public static void setUp()
	{
		baseURI = "http://localhost";
		port = 9090;
		basePath = "/laptop-bag/webapi/api";
	}
	
	public BaseClass()
	{
		baseURI = "http://localhost";
		port = 9090;
		basePath = "/laptop-bag/webapi/api";
	}

}

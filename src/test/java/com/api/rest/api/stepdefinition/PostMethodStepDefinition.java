package com.api.rest.api.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import com.api.cucumber.transform.TransformerData;
import com.api.rest.api.restassuredhelper.transformer.TransformData;
import com.api.util.RestModel;
import com.api.util.RestUtil;

import cucumber.api.Transform;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostMethodStepDefinition{
	/*private RequestSpecification requestSpecification;
	private Response response;*/
	private String jsonBody;
	
	private RestModel restModel;
	
	public PostMethodStepDefinition(RestModel restModel) {
		this.restModel = restModel;
	}
	@Given("Accept the content in JSON format")
	public void accept_the_content_in_JSON_format() {
		restModel.requestSpecification = given().accept(ContentType.JSON);
	}

	@Given("Content type as JSON")
	public void content_type_as_JSON() {
		restModel.requestSpecification.contentType(ContentType.JSON);
	}

	@When("Post request is performed with BrandName as {string}, Features as {string}, Laptop as {string}")
	public void post_request_is_performed_with_BrandName_as_Features_as_Laptop_as(String brandName, String Features, String laptopName) {
	    String  body = RestUtil.getJsonBody(brandName, null, laptopName, Arrays.asList(Features.split(",")));
	    restModel.response = restModel.requestSpecification.body(body).post("/add");
	}
	
	@When("Post request is performed with details {string}")
	public void post_request_is_performed_with_details(@Transform(TransformData.class)List<String> list) {
	    String  body = RestUtil.getJsonBody(list.get(0), null, list.get(list.size()-1), list.subList(1, list.size()-1));
	    restModel.response = restModel.requestSpecification.body(body).post("/add");
	}

	@Then("status code {string} should return")
	public void status_code_should_return(String statusCode) {
		restModel.response.then().assertThat().equals(Integer.parseInt(statusCode));
	}

	@Then("response should have the integer Id")
	public void response_should_have_the_integer_Id() {
		restModel.response.then().assertThat().body("Id", is(Integer.class));
	}
	
	@But("invalid JSON body is supplied")
	public void invalid_JSON_body_is_supplied() {
	 jsonBody = "Invalid Body";
	}

	@When("perform the Post request")
	public void perform_the_Post_request() {
		restModel.response = restModel.requestSpecification.body(jsonBody).post("/add");
	}
	
	@Given("user enters the url as {string}")
	public void user_enters_the_url_as(@Transform(TransformerData.class)String string) {
	    System.out.println(string);
	}
}

package com.api.rest.api.stepdefinition;

import java.util.Arrays;

import com.api.util.RestModel;
import com.api.util.RestUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PutMethodStepDefinition {
	
	private RestModel restModel;
	private String id; //To store the id coming from response
	
	public PutMethodStepDefinition(RestModel restModel) {
	 this.restModel = restModel;
	}

	
	//Instead of below appraoch, we can choose to use @Transform(TransformData.class)List<String> to prepare the Json body.
	@When("Put request is performed with id and details like BrandName as {string}, Features as {string}, Laptop as {string}")
	public void put_request_is_performed_with_id_and_details_like_BrandName_as_Features_as_Laptop_as(String brandName, String Features, String laptopName) {
	    id = restModel.response.thenReturn().jsonPath().getString("Id");
	    String  body = RestUtil.getJsonBody(brandName, Integer.parseInt(id), laptopName, Arrays.asList(Features.split(",")));
	    restModel.response = restModel.requestSpecification.body(body).put("/update");
	}

	@Then("details should get updated")
	public void details_should_get_updated() {
		System.out.println("Put Response ==> "+restModel.response.asString());
	}
}

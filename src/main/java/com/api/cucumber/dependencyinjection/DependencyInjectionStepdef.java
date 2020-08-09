package com.api.cucumber.dependencyinjection;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class DependencyInjectionStepdef {
	//1. To create a reference variable in the dependent class
	//2. To initialize the reference variable via Constructor

	private BaseClassDI baseClassDI;
	public DependencyInjectionStepdef(BaseClassDI baseClassDI) {
		this.baseClassDI = baseClassDI;
	}
	
	@Given("user should be logged in")
	public void user_should_be_logged_in() {
	   System.out.println("Background 1 =="+baseClassDI.getFeatureName());
	   System.out.println("Background 1 =="+baseClassDI.getScenarioName());
	}

	@Given("he lands on wall")
	public void he_lands_on_wall() {
		  System.out.println("Background 2");
	}

	@Given("When I type the text as {string} in text box")
	public void when_I_type_the_text_as_in_text_box(String string) {
		  System.out.println("Given statement");
	}

	@And("click on Post button")
	public void click_on_Post_button() {
		System.out.println("And statement");
	}

	@Then("the message should get posted")
	public void the_message_should_get_posted() {
		System.out.println("Then statement");
	}
	
}

package com.api.rest.api.stepdefinition;

import java.util.List;

import com.api.rest.api.restassuredhelper.transformer.User;

import io.cucumber.datatable.DataTable;
/*import cucumber.api.DataTable;*/
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
	
	@Given("User is on the login page")
	public void user_is_on_the_login_page() {
	    System.out.println("Statement1");
	}

	@When("user logs in with the following username and password")
	public void user_logs_in_with_the_following_username_and_password(DataTable dataTable) {
		
		List<User> userList= dataTable.asList(User.class);
		for(User user: userList)
		{
			System.out.println(user.toString());
		}
	}

	@Given("a precondition has value {string}")
	public void a_precondition_has_value(String string) {
		System.out.println("Statement2");
	}

	@Given("something with {string}")
	public void something_with(String string) {
		System.out.println("Statement3");
	}

	@Then("check <{string}> is the output")
	public void check_is_the_output(String string) {
		System.out.println("Statement4");
	}
}

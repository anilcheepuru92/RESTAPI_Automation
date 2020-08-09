package com.api.rest.api.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/com/api/rest/api/featurefile/Login.feature"},
		glue={"com.api.rest.api.stepdefinition","com.api.rest.api.restassuredhelper.transformer"},
		monochrome=true,
		dryRun=false
		)
public class LoginRunner {

}

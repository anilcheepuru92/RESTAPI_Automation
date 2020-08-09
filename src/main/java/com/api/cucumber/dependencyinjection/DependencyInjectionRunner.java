package com.api.cucumber.dependencyinjection;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@CucumberOptions(features = {"src/main/java/com/api/cucumber/dependencyinjection/DependencyInjection.feature"},
glue={"com.api.cucumber.dependencyinjection"},
monochrome=true,
dryRun=false		
		)
@RunWith(Cucumber.class)

public class DependencyInjectionRunner {

}

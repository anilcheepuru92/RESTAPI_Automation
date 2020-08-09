package com.api.cucumber.dependencyinjection;

public class BaseClassDI {
	
	private String featureName;
	private String scenarioName;
	
	public String getFeatureName() {
		return featureName;
	}
	
	public String getScenarioName() {
		return scenarioName;
	}
	
	public BaseClassDI()
	{
		featureName = "BDD";
		scenarioName = "DI";
	}
}

package com.cucumber.hooks;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class GeneralHooks {
	//1. Create the public method
	//2. User the @Before, @After annotations with the method
	//3. Specify the package in the runner
	//4. Inject the Scenario Object in the hook method
	@Before
	public void setup(Scenario scenario)
	{
	System.out.println("=======Before method======");
	System.out.println(scenario.getName());
	System.out.println(scenario.getStatus());
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
		System.out.println("=======After method=======");
		System.out.println(scenario.getName());
		System.out.println(scenario.getStatus());
	}
}

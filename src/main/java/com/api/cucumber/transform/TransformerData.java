package com.api.cucumber.transform;
import cucumber.api.Transformer;

public class TransformerData extends Transformer<String>{

	@Override
	public String transform(String arg) {
		return arg+"Transformed"; 
	}
}

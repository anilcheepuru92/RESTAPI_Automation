package com.api.rest.api.restassuredhelper.transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cucumber.api.Transformer;

public class TransformData extends Transformer<List<String>>{

	@Override
	public List<String> transform(String value) {
		String[] data = value.split(",");
		List<String> convertedList = Arrays.asList(data);
		return convertedList;
	}

}

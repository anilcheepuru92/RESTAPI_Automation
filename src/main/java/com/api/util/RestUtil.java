package com.api.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class RestUtil {

	private static final String BRANDNAME = "BrandName";
	private static final String FEATURES = "Features";
	private static final String FEATURE = "Feature";
	private static final String _Id = "Id";
	private static final String LAPTOPNAME = "LaptopName";
	
	private static int getRandomId()
	{
		return (int)(1000*(Math.random()));
	}
	
	public static String getJsonBody(String brandName, Integer id, String laptopName, List<String> feature)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(BRANDNAME, brandName);
		jsonObject.addProperty(_Id, (id==null)?getRandomId()+"":id+"");
		
		JsonObject featureObject = new JsonObject();
		JsonArray jsonArray = getJsonArray(feature);		
		featureObject.add(FEATURE, jsonArray);
		jsonObject.add(FEATURES, featureObject);
		
		jsonObject.addProperty(LAPTOPNAME, laptopName);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	private static JsonArray getJsonArray(List<String> feature) {
		JsonArray jsonArray = new JsonArray();
		for(String jsonElement: feature)
		{
			jsonArray.add(jsonElement);
		}
		return jsonArray;
	}
	
	/*@Test
	public void test()
	{
		List<String> featureList = new ArrayList<>();
		featureList.add("8GB RAM");
		featureList.add("1TB Hard drive");
		
		System.out.println(RestUtil.getJsonBody("Apple", null, "Macbook Pro", featureList));

	}*/
}

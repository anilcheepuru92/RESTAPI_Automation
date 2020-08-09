package com.api.rest.api.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestPost {

	//@Test
	public void testPost() {
		String id = (int)(1000*(Math.random()))+"";
		String postJsonBody = "{"+
				 "\"BrandName\": \"Dell\","+
				 "\"Features\": {"+
				  "\"Feature\": [\"8GB RAM\","+
				  "\"1TB Hard Drive\"]"+
				 "},"+
				 "\"Id\":"+ id+","+
				 "\"LaptopName\": \"Latitude\""+
				"}";
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestAPIHelper.performPostRequest("http://localhost:9090/laptop-bag/webapi/api/add", postJsonBody, ContentType.APPLICATION_JSON,headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(id,body.Id);
		Assert.assertEquals("Latitude", body.LaptopName);
	}
	
	@Test
	public void testPostWithXML() {
		String id = (int)(1000*(Math.random()))+"";
		String postJsonBody = "{"+
				 "\"BrandName\": \"Dell\","+
				 "\"Features\": {"+
				  "\"Feature\": [\"8GB RAM\","+
				  "\"1TB Hard Drive\"]"+
				 "},"+
				 "\"Id\":"+ id+","+
				 "\"LaptopName\": \"Latitude\""+
				"}";
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestAPIHelper.performPostRequest("http://localhost:9090/laptop-bag/webapi/api/add", postJsonBody, ContentType.APPLICATION_JSON,headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		XmlMapper xml = new XmlMapper();
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			ResponseBody body = xml.readValue(response.getResponseBody(), ResponseBody.class);
			System.out.println(body);
			Assert.assertEquals("Dell", body.BrandName);
			Assert.assertEquals("Latitude", body.LaptopName);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}

package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGetMethod {
	@Test
	public void testGetPingAlive()
	{
		String url = "http://localhost:9090/laptop-bag/webapi/api/ping/Anil";	
		RestResponse response = RestAPIHelper.performGetRequest(url, null);
		System.out.println(response.toString());
		Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
		Assert.assertEquals("Hi! Anil",response.getResponseBody());
	}
	
	@Test
	public void testGetAll()
	{
		String url = "http://localhost:9090/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestAPIHelper.performGetRequest(url, headers);
		Assert.assertTrue((HttpStatus.SC_OK==response.getStatusCode()) || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()));
		System.out.println(response.getResponseBody());
	}
	
	@Test
	public void testGetFindWithId()
	{
		String url = "http://localhost:9090/laptop-bag/webapi/api/find/127";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestAPIHelper.performGetRequest(url, headers);
		Assert.assertTrue((HttpStatus.SC_OK==response.getStatusCode()) || (HttpStatus.SC_NOT_FOUND==response.getStatusCode()));
		System.out.println(response.getResponseBody());
		/*
		 * Step1: Use the GSON builder class to get the instance of GSON class
		 * Step2: Use the GSON object to de serialize the JSON
		 */
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody responseBody = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		System.out.println(responseBody);
		Assert.assertEquals("Sony", responseBody.BrandName);
		Assert.assertEquals("127", responseBody.Id);
	}
	
	@Test
	public void testSecureGet()
	{
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/json");
		//headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");
		headers.put("Authorization", Base64.encodeBase64String("admin:welcome".getBytes()));
		RestResponse response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/secure/find/all", headers);
		System.out.println(response.toString());
	}
}

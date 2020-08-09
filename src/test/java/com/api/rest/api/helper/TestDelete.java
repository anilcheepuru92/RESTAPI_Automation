package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.RestResponse;

public class TestDelete {
	/*
	 * Step1: Post the data and validate the status code to be 200 OK
	 * Step2: Call delete endpoint to delete the above posted data,validate for 200 OK
	 * Step3: Call the get endpoint and validate the status code to be 404 Not Found
	 */
	@Test
	public void testDelete()
	{
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
		
		response = RestAPIHelper.performDelete("http://localhost:9090/laptop-bag/webapi/api/delete/"+id, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
	}

}

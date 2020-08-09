package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestAsyncClient {
	/*
	 *Step1: Post the data, verify the 200 OK 
	 *Step2: Get the data using GET end point, verify the id and status code 
	 *Step3: Update the data in the container using PUT end point, verify the status code  
	 *Step4: Get the data using GET end point, verify the id and status code
	 *Step5: Delete the data using DELETE end point, verify the status code 
	 *Step6: Get the data using GET end point, verify the id and status code 
	 */
	public static void main(String[] args) {
		testClient();
	}
	//@Test
	public static void testClient()
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
		
		String putJsonBody = "{"+
				 "\"BrandName\": \"Dell\","+
				 "\"Features\": {"+
				  "\"Feature\": [\"16GB RAM\","+
				  "\"3TB Hard Drive\"]"+
				 "},"+
				 "\"Id\":"+ id+","+
				 "\"LaptopName\": \"Inspiron\""+
				"}";
		
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = HttpAsyncClientHelper.performPostRequestAsync("https://localhost:9090/laptop-bag/webapi/api/add", postJsonBody, ContentType.APPLICATION_JSON,headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = HttpAsyncClientHelper.performGetAsyncRequest("https://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = HttpAsyncClientHelper.performPutRequestAsync("https://localhost:9090/laptop-bag/webapi/api/update", putJsonBody , ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = HttpAsyncClientHelper.performGetAsyncRequest("https://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals("Inspiron", body.LaptopName);
		
		response = HttpAsyncClientHelper.performDeleteRequestAsync("https://localhost:9090/laptop-bag/webapi/api/delete/"+id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = HttpAsyncClientHelper.performGetAsyncRequest("https://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
	}
}

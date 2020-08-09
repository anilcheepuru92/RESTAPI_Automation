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


public class TestGetPostWithSSL {

	@Test
	public void testGetPost()
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
		RestResponse response = HttpsClientHelper.performPostRequestWithSSL("https://localhost:9090/laptop-bag/webapi/sslres/add", postJsonBody, ContentType.APPLICATION_JSON,headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		HttpsClientHelper.performGetRequestWithSSL("https://localhost:9090/laptop-bag/webapi/sslres/find/"+id, headers);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(id, body.Id);
		Assert.assertEquals("Latitude", body.LaptopName);
	}
}

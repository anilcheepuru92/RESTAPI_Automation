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

public class TestPut {
	/*
	 * Step1: Call the Post method to create a record
	 * Step2: Call teh Put method to update the record
	 * Step3: Call the get method to validate the output, Content Validation
	 * 
	 */
	@Test
	public void testPut()
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
		
		String xmlBody = "<Laptop>"+
			    "<BrandName>Dell</BrandName>"+
			    "<Features>"+
			        "<Feature>8GB RAM</Feature>"+
			        "<Feature>1TB Hard Drive</Feature>"+
			        "<Feature>18 inch LED display</Feature>"+
			    "</Features>"+
			    "<Id>"+id+"</Id>"+
			    "<LaptopName>Longitude</LaptopName>"+
			"</Laptop>";
		
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestAPIHelper.performPostRequest("http://localhost:9090/laptop-bag/webapi/api/add", postJsonBody, ContentType.APPLICATION_JSON,headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		headers.clear();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		response = RestAPIHelper.performPut("http://localhost:9090/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/api/find/"+id, headers);
		XmlMapper mapper = new XmlMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			ResponseBody body = mapper.readValue(response.getResponseBody(), ResponseBody.class);
			Assert.assertEquals("Longitude", body.LaptopName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
} 

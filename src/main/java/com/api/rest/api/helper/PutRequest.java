package com.api.rest.api.helper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class PutRequest {

	public static void main(String[] args) {
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
		/*Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");*/
		
		String xmlBody = "<Laptop>"+
    "<BrandName>Dell</BrandName>"+
    "<Features>"+
        "<Feature>8GB RAM</Feature>"+
        "<Feature>1TB Hard Drive</Feature>"+
        "<Feature>18 inch LED display</Feature>"+
    "</Features>"+
    "<Id>1262</Id>"+
    "<LaptopName>Latitude</LaptopName>"+
"</Laptop>";
		/*RequestBuilder buildPut = RequestBuilder.put("http://localhost:9090/laptop-bag/webapi/api/update").setHeader("Content-Type","Application/xml").
		addHeader("Accept","Application/xml");
		HttpUriRequest put = buildPut.setEntity(new StringEntity(xmlBody,ContentType.APPLICATION_XML)).build();
	try(CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(put))
	{
		ResponseHandler<String> handler = new BasicResponseHandler();
		RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		System.out.println(restResponse.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}*/
		Map<String, String> headers = new HashMap();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		RestResponse response = RestAPIHelper.performPut("http://localhost:9090/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		System.out.println(response.toString());
}
}

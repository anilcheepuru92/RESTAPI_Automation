package com.api.rest.api.helper;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class DeleteRequest {

	public static void main(String[] args) {
	/*HttpUriRequest delete = RequestBuilder.delete("http://localhost:9090/laptop-bag/webapi/api/delete/126").build();
	
	try(CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(delete);)
	{
		ResponseHandler<String> handler = new BasicResponseHandler();
		RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		System.out.println(restResponse.toString());
	} 
	catch (Exception e) {
		e.printStackTrace();
	}*/
	
		RestResponse response = RestAPIHelper.performDelete("http://localhost:9090/laptop-bag/webapi/api/delete/1262", null);
		System.out.println(response.toString());
	}
}

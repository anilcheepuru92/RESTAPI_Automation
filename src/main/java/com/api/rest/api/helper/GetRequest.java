package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import com.api.rest.api.model.RestResponse;

import io.restassured.RestAssured;

public class GetRequest {

	public static void main(String[] args) {

			/*try {
				HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/Anil");
				CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get);
				StatusLine status = response.getStatusLine();
				System.out.println(status.getStatusCode());
				System.out.println(status.getProtocolVersion());
				client.close();
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		/*HttpGet get = new HttpGet("http://localhost:9090/laptop-bag/webapi/api/ping/Anil");
		try(
				CloseableHttpClient client = HttpClientBuilder.create().build();	
				CloseableHttpResponse response = client.execute(get);)
		{
			StatusLine status = response.getStatusLine();
			System.out.println(status.getStatusCode());
			System.out.println(status.getProtocolVersion());
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			String getBody = body.handleResponse(response);
			System.out.println(getBody);
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/
		//http://localhost:9090/laptop-bag/webapi/api/all
		/*RestResponse response = RestAPIHelper.performGetRequest("http://localhost:9090/laptop-bag/webapi/api/ping/Anil", null);
		System.out.println(response.toString());*/
		Map<String, String> headers = new HashMap();
		headers.put("Content-Type", "application/json");
		RestResponse response = HttpAsyncClientHelper.performGetAsyncRequest("http://localhost:9090/laptop-bag/webapi/api/ping/Anil", headers);
		System.out.println(response.toString());
	}
}

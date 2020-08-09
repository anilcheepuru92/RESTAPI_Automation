package com.api.rest.api.helper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;

import com.api.rest.api.model.RestResponse;

public class PostRequest {

	public static void main(String[] args) {
		
	HttpPost post = new HttpPost("http://localhost:9090/laptop-bag/webapi/api/add");
	/*try(CloseableHttpClient client = HttpClientBuilder.create().build())
	{*/
		
		String postJsonBody = "{"+
 "\"BrandName\": \"Dell\","+
 "\"Features\": {"+
  "\"Feature\": [\"8GB RAM\","+
  "\"1TB Hard Drive\"]"+
 "},"+
 "\"Id\":"+ (int)(1000*(Math.random()))+","+
 "\"LaptopName\": \"Latitude\""+
"}";
		/*post.addHeader("Content-Type", "application/json");
		post.addHeader("Accept", "application/json");
		
		//Uncomment these lines if request is sent by a String 
		StringEntity data = new StringEntity(postJsonBody, ContentType.APPLICATION_JSON);
		post.setEntity(data);
		
		
		File file = new File("PostRequestFile");
		FileEntity data = new FileEntity(file, ContentType.APPLICATION_JSON);
		post.setEntity(data);
		
		CloseableHttpResponse response = client.execute(post);
		ResponseHandler<String> handler = new BasicResponseHandler();
		RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		System.out.println(restResponse.toString());
	}
	catch(Exception e)
	{
		
	}*/
		File file = new File("PostRequestFile");
	Map<String, String> headers = new HashMap();
	headers.put("Accept", "application/json");
	headers.put("Content-Type", "application/json");
	RestResponse response = RestAPIHelper.performPostRequest("http://localhost:9090/laptop-bag/webapi/api/add", file, ContentType.APPLICATION_JSON,headers);
	System.out.println(response.getStatusCode());
	System.out.println(response.getResponseBody());
	
	}
	}

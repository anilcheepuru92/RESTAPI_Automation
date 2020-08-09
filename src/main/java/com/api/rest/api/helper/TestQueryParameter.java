package com.api.rest.api.helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.api.rest.api.model.RestResponse;

public class TestQueryParameter {

	public void testQuery()
	{
		//new URIBuilder("http://localhost:9090/laptop-bag/webapi/api/query?id=\"1\"&laptopName=\"Dell\"");
		
		try {
			URI uri = new URIBuilder().
			setScheme("http").
			setHost("localhost:9090/").
			setPath("laptop-bag/webapi/api/query").
			setParameter("Id", "1").setParameter("LaptopName", "MacBook Pro").build();
			RestResponse response = RestAPIHelper.performGetRequest(uri, null);
			System.out.println(response.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

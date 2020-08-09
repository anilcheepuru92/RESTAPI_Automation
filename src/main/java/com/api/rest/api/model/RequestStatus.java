package com.api.rest.api.model;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public class RequestStatus implements FutureCallback<HttpResponse>{

	public void completed(HttpResponse result) {
		System.out.println("Request completed -->"+result.getProtocolVersion());
		
	}

	public void failed(Exception ex) {
		System.out.println("Request failed -->"+ex.getMessage());
	}

	public void cancelled() {
		System.out.println("Request cancelled -->");
	}

	
}

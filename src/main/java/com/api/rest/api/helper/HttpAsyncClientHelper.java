package com.api.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.api.rest.api.model.RequestStatus;
import com.api.rest.api.model.RestResponse;

public class HttpAsyncClientHelper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static Header[] getCustomHeaders(Map<String,String> headers)
	{
		Header[] customHeaders = new Header[headers.size()];
		int i=0;
		for(String key: headers.keySet())
		{
		customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
	}
	
	private static HttpEntity getHttpEntity(Object object, ContentType type)
	{
		if(object instanceof String)
		{
			return new StringEntity((String)object, type);
		}
		else if(object instanceof File)
		{
			return new FileEntity((File)object, type);
		}
		else throw new RuntimeException("Entity not found");
	}
	
	public static SSLContext getSSLContext()
	{
		TrustStrategy trust = new TrustStrategy(){
			public boolean isTrusted(X509Certificate[] chain, String authTyp) throws CertificateException
			{
				return true;
			}
		};
		try {
			return SSLContextBuilder.create().loadTrustMaterial(trust).build();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static CloseableHttpAsyncClient getHttpAsyncClient(SSLContext context)
	{
		if(context== null)
		{
			return HttpAsyncClientBuilder.create().build();
		}
		return HttpAsyncClientBuilder.create().setSSLContext(context).build();
	}
	
	private static RestResponse performRequest(HttpUriRequest method, SSLContext context)
	{
		Future<HttpResponse> response = null;
		try(CloseableHttpAsyncClient client = getHttpAsyncClient(context))
		{
			client.start();
			//response = client.execute(method, null); //this line is replaced by below line
			response = client.execute(method, new RequestStatus());
			ResponseHandler<String> handler = new BasicResponseHandler();
			return new RestResponse(response.get().getStatusLine().getStatusCode(), handler.handleResponse(response.get()));
		}
		catch(Exception e)
		{
			if(e instanceof HttpResponseException)
			{
				try {
					return new RestResponse(response.get().getStatusLine().getStatusCode(), e.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performGetAsyncRequest(String uri, Map<String, String> headers)
	{
		try {
			return performGetAsyncRequest(new URI(uri), headers);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static RestResponse performGetAsyncRequest(URI uri, Map<String, String> headers)
	{
		HttpGet get = new HttpGet(uri);
		if(headers!=null)
		{
			get.setHeaders(getCustomHeaders(headers));
		}
		try {
			return performRequest(get, null);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static RestResponse performPostRequestAsync(URI uri, Object body, ContentType type, Map<String,String> headers)
	{
		HttpPost post = new HttpPost(uri);
		if(headers!=null)
		{
			post.setHeaders(getCustomHeaders(headers));
		}
		post.setEntity(getHttpEntity(body, type));
		return performRequest(post, null);
	}
	
	public static RestResponse performPostRequestAsync(String uri, Object body, ContentType type, Map<String,String> headers)
	{
		try {
			return performPostRequestAsync(new URI(uri), body, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performPutRequestAsync(URI uri, Object body, ContentType type, Map<String,String> headers)
	{
		HttpPut put = new HttpPut(uri);
		if(headers!=null)
		{
			put.setHeaders(getCustomHeaders(headers));
		}
		put.setEntity(getHttpEntity(body, type));
		return performRequest(put, null);
	}
	
	public static RestResponse performPutRequestAsync(String uri, Object body, ContentType type, Map<String,String> headers)
	{
		try {
			return performPutRequestAsync(new URI(uri), body, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performDeleteRequestAsync(URI uri, Map<String,String> headers)
	{
		HttpDelete put = new HttpDelete(uri);
		if(headers!=null)
		{
			put.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(put, null);
	}
	
	public static RestResponse performDeleteRequestAsync(String uri, Map<String,String> headers)
	{
		try {
			return performDeleteRequestAsync(new URI(uri), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performGetSSLAsyncRequest(String uri, Map<String, String> headers)
	{
		try {
			return performGetAsyncRequest(new URI(uri), headers);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static RestResponse performGetSSLAsyncRequest(URI uri, Map<String, String> headers)
	{
		HttpGet get = new HttpGet(uri);
		if(headers!=null)
		{
			get.setHeaders(getCustomHeaders(headers));
		}
		try {
			return performRequest(get, getSSLContext());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static RestResponse performPostSSLRequestAsync(URI uri, Object body, ContentType type, Map<String,String> headers)
	{
		HttpPost post = new HttpPost(uri);
		if(headers!=null)
		{
			post.setHeaders(getCustomHeaders(headers));
		}
		post.setEntity(getHttpEntity(body, type));
		return performRequest(post, getSSLContext());
	}
	
	public static RestResponse performPostSSLRequestAsync(String uri, Object body, ContentType type, Map<String,String> headers)
	{
		try {
			return performPostRequestAsync(new URI(uri), body, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performPutSSLRequestAsync(URI uri, Object body, ContentType type, Map<String,String> headers)
	{
		HttpPut put = new HttpPut(uri);
		if(headers!=null)
		{
			put.setHeaders(getCustomHeaders(headers));
		}
		put.setEntity(getHttpEntity(body, type));
		return performRequest(put, getSSLContext());
	}
	
	public static RestResponse performPutSSLRequestAsync(String uri, Object body, ContentType type, Map<String,String> headers)
	{
		try {
			return performPutRequestAsync(new URI(uri), body, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static RestResponse performDeleteSSLRequestAsync(URI uri, Map<String,String> headers)
	{
		HttpDelete put = new HttpDelete(uri);
		if(headers!=null)
		{
			put.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(put, getSSLContext());
	}
	
	public static RestResponse performDeleteSSLRequestAsync(String uri, Map<String,String> headers)
	{
		try {
			return performDeleteRequestAsync(new URI(uri), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

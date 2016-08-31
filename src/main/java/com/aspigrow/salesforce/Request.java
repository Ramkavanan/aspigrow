package com.aspigrow.salesforce;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class Request {
	
	private String url;
	
	private String access_token;
	
	private String instance_url;
	
	private String body;
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	public Request() {
		
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return this.instance_url + this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getInstance_url() {
		return instance_url;
	}

	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
}
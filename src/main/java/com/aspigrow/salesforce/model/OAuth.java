package com.aspigrow.salesforce.model;

import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.persistence.Transient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OAuth class provide the login information to applications
 * 
 * @author ramachandran
 */

@JsonIgnoreProperties(ignoreUnknown=true)   
public final class OAuth {
	
	@JsonProperty("access_token")
	private String access_token;
	
	@JsonProperty("signature")
	private String signature;
	
	@JsonProperty("instance_url")
	private String instance_url;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("token_type")
	private String token_type;
	
	@JsonProperty("issued_at")
	private String issued_at;
	
	private boolean isLoggedIn = false;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getInstance_url() {
		return instance_url;
	}

	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getIssued_at() {
		return issued_at;
	}

	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("OAuth : {");
		sb.append("token_type=").append(token_type).append(",");
		sb.append("access_token = ").append(access_token).append(",");
		sb.append("instance_url = ").append(instance_url).append("}");
		return sb.toString();
	}
}
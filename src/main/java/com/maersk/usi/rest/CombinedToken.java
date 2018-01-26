package com.maersk.usi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CombinedToken {
    private String id_token;
    private String access_token;
    private String token_type;
    private long expires_in;

    public CombinedToken() {
    }



	/**
	* Returns value of id_token
	* @return
	*/
	public String getId_token() {
		return id_token;
	}

	/**
	* Sets new value of id_token
	* @param
	*/
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	/**
	* Returns value of access_token
	* @return
	*/
	public String getAccess_token() {
		return access_token;
	}

	/**
	* Sets new value of access_token
	* @param
	*/
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	* Returns value of token_type
	* @return
	*/
	public String getToken_type() {
		return token_type;
	}

	/**
	* Sets new value of token_type
	* @param
	*/
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	* Returns value of expires_in
	* @return
	*/
	public long getExpires_in() {
		return expires_in;
	}

	/**
	* Sets new value of expires_in
	* @param
	*/
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
}

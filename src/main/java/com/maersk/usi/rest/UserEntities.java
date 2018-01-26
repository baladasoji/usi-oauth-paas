package com.maersk.usi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntities {

    private Long length;
    private String message;
    private DCInfo userEntities ;

    public UserEntities() {
    }

/*    @Override
    public String toString() {
        return "UserEntities{" +
                "length=" + length +
                ", message='" + message + '\'' +
                ", userinfo='" + userEntities + '\'' +
                '}';
    }
*/
	/**
	* Returns value of length
	* @return
	*/
	public Long getLength() {
		return length;
	}

	/**
	* Sets new value of length
	* @param
	*/
	public void setLength(Long length) {
		this.length = length;
	}

	/**
	* Returns value of message
	* @return
	*/
	public String getMessage() {
		return message;
	}

	/**
	* Sets new value of message
	* @param
	*/
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	* Returns value of userEntities
	* @return
	*/
	public DCInfo getUserEntities() {
		return userEntities;
	}

	/**
	* Sets new value of userEntities
	* @param
	*/
	public void setUserEntities(DCInfo userEntities) {
		this.userEntities = userEntities;
	}
}

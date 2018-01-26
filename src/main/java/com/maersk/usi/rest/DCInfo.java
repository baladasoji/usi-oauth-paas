package com.maersk.usi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DCInfo {

    private String output;

    public DCInfo() {
    }


	/**
	* Returns value of userId
	* @return
	*/
	public String getOutput() {
		return output;
	}

	/**
	* Sets new value of userId
	* @param
	*/
	public void setOutput(String output) {
		this.output = output;
	}

}

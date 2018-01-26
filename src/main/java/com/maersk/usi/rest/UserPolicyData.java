package com.maersk.usi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPolicyData {


    private String cn;
    private String usiRoleName;
    private String usiProtectionKey;
    private String usiProtectedEntities;
    private String description;

    public UserPolicyData() {
    }


    @Override
    public String toString() {
        return cn + "    "+ description ;
    }

	/**
	* Returns value of cn
	* @return
	*/
	public String getCn() {
		return cn;
	}

	/**
	* Sets new value of cn
	* @param
	*/
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	* Returns value of usiRoleName
	* @return
	*/
	public String getUsiRoleName() {
		return usiRoleName;
	}

	/**
	* Sets new value of usiRoleName
	* @param
	*/
	public void setUsiRoleName(String usiRoleName) {
		this.usiRoleName = usiRoleName;
	}

	/**
	* Returns value of usiProtectionKey
	* @return
	*/
	public String getUsiProtectionKey() {
		return usiProtectionKey;
	}

	/**
	* Sets new value of usiProtectionKey
	* @param
	*/
	public void setUsiProtectionKey(String usiProtectionKey) {
		this.usiProtectionKey = usiProtectionKey;
	}

	/**
	* Returns value of usiProtectedEntities
	* @return
	*/
	public String getUsiProtectedEntities() {
		return usiProtectedEntities;
	}

	/**
	* Sets new value of usiProtectedEntities
	* @param
	*/
	public void setUsiProtectedEntities(String usiProtectedEntities) {
		this.usiProtectedEntities = usiProtectedEntities;
	}

	/**
	* Returns value of description
	* @return
	*/
	public String getDescription() {
		return description;
	}

	/**
	* Sets new value of description
	* @param
	*/
	public void setDescription(String description) {
		this.description = description;
	}
}

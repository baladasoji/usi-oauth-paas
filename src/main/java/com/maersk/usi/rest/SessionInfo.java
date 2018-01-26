package com.maersk.usi.rest;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionInfo {
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String state;
    private String email;
    private String scvPersonId;
    private String userType;
    private String officeName;
    private String authenticationType;
    private String loggedOnPrincipal;
    private String employeeid_x509;
    private String employeeid;
    private String carrier;
    private String pp_custom_timeout;
    private String sessionid;
    private String roles;
    private String profile;
    private List <UserPolicyData> userPolicyDataEntity;

    public SessionInfo() {
    }


	/**
	* Returns value of firstName
	* @return
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* Sets new value of firstName
	* @param
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	* Returns value of lastName
	* @return
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* Sets new value of lastName
	* @param
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	* Returns value of city
	* @return
	*/
	public String getCity() {
		return city;
	}

	/**
	* Sets new value of city
	* @param
	*/
	public void setCity(String city) {
		this.city = city;
	}

	/**
	* Returns value of country
	* @return
	*/
	public String getCountry() {
		return country;
	}

	/**
	* Sets new value of country
	* @param
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	* Returns value of state
	* @return
	*/
	public String getState() {
		return state;
	}

	/**
	* Sets new value of state
	* @param
	*/
	public void setState(String state) {
		this.state = state;
	}

	/**
	* Returns value of email
	* @return
	*/
	public String getEmail() {
		return email;
	}

	/**
	* Sets new value of email
	* @param
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	* Returns value of scvPersonId
	* @return
	*/
	public String getScvPersonId() {
		return scvPersonId;
	}

	/**
	* Sets new value of scvPersonId
	* @param
	*/
	public void setScvPersonId(String scvPersonId) {
		this.scvPersonId = scvPersonId;
	}

	/**
	* Returns value of userType
	* @return
	*/
	public String getUserType() {
		return userType;
	}

	/**
	* Sets new value of userType
	* @param
	*/
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	* Returns value of officeName
	* @return
	*/
	public String getOfficeName() {
		return officeName;
	}

	/**
	* Sets new value of officeName
	* @param
	*/
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/**
	* Returns value of authenticationType
	* @return
	*/
	public String getAuthenticationType() {
		return authenticationType;
	}

	/**
	* Sets new value of authenticationType
	* @param
	*/
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	/**
	* Returns value of loggedOnPrincipal
	* @return
	*/
	public String getLoggedOnPrincipal() {
		return loggedOnPrincipal;
	}

	/**
	* Sets new value of loggedOnPrincipal
	* @param
	*/
	public void setLoggedOnPrincipal(String loggedOnPrincipal) {
		this.loggedOnPrincipal = loggedOnPrincipal;
	}

	/**
	* Returns value of employeeid_x509
	* @return
	*/
	public String getEmployeeid_x509() {
		return employeeid_x509;
	}

	/**
	* Sets new value of employeeid_x509
	* @param
	*/
	public void setEmployeeid_x509(String employeeid_x509) {
		this.employeeid_x509 = employeeid_x509;
	}

	/**
	* Returns value of employeeid
	* @return
	*/
	public String getEmployeeid() {
		return employeeid;
	}

	/**
	* Sets new value of employeeid
	* @param
	*/
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	/**
	* Returns value of carrier
	* @return
	*/
	public String getCarrier() {
		return carrier;
	}

	/**
	* Sets new value of carrier
	* @param
	*/
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	* Returns value of pp_custom_timeout
	* @return
	*/
	public String getPp_custom_timeout() {
		return pp_custom_timeout;
	}

	/**
	* Sets new value of pp_custom_timeout
	* @param
	*/
	public void setPp_custom_timeout(String pp_custom_timeout) {
		this.pp_custom_timeout = pp_custom_timeout;
	}

	/**
	* Returns value of sessionid
	* @return
	*/
	public String getSessionid() {
		return sessionid;
	}

	/**
	* Sets new value of sessionid
	* @param
	*/
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	/**
	* Returns value of roles
	* @return
	*/
	public String getRoles() {
		return roles;
	}

	/**
	* Sets new value of roles
	* @param
	*/
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	* Returns value of profile
	* @return
	*/
	public String getProfile() {
		return profile;
	}

	/**
	* Sets new value of profile
	* @param
	*/
	public void setProfile(String profile) {
		this.profile = profile;
	}
  /**
	* Returns value of profile
	* @return
	*/
	public List<UserPolicyData> getUserPolicyDataEntity() {
		return userPolicyDataEntity;
	}

	/**
	* Sets new value of profile
	* @param
	*/
	public void setUserPolicyDataEntity(List<UserPolicyData> userPolicyDataEntity) {
		this.userPolicyDataEntity = userPolicyDataEntity;
	}
}

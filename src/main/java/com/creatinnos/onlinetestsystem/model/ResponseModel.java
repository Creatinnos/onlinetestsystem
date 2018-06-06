package com.creatinnos.onlinetestsystem.model;

import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

public class ResponseModel {
	private String responseMessage;
	private OrganizationUsers organizationUsers;
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public OrganizationUsers getOrganizationUsers() {
		return organizationUsers;
	}
	public void setOrganizationUsers(OrganizationUsers organizationUsers) {
		this.organizationUsers = organizationUsers;
	}
	
	
}

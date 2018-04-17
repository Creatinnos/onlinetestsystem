package com.creatinnos.onlinetestsystem.model;

public class LoginModel {

	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String companyName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "LoginModel [userName=" + userName + ", password=" + password + ", email=" + email + ", phoneNUmber="
				+ phoneNumber + ", companyName=" + companyName + "]";
	}

}

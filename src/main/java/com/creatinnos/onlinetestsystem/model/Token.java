package com.creatinnos.onlinetestsystem.model;

public class Token {

	private String token = "token"+Math.random();


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

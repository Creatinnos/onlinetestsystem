package com.creatinnos.onlinetestsystem.securityhandling;

import java.util.HashMap;

public class SecurityUtil {

	static SecurityUtil securityUtil=new SecurityUtil();
	
	private SecurityUtil()
	{
		
	}
	
	public static SecurityUtil getInstance()
	{
		return securityUtil;
	}
	
	
	private static HashMap<String, SecurityModal> tokenMap = new HashMap<>();

	public String addToken(SecurityModal securityModal) {
		String token = "" + Math.random();
		tokenMap.put(token, securityModal);
		return token;
	}

	public SecurityModal getToken(String token) {
		return tokenMap.get(token);
	}

	public void removeToken(String token) {
		tokenMap.remove(token);
	}

}

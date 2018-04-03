package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.creatinnos.onlinetestsystem.bo.UserInfo;
import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.daocustomization.InvokeSetterGetter;
import com.creatinnos.onlinetestsystem.model.LoginModel;

public class LoginDAO {

	public List<UserInfo> find(LoginModel loginModel,String... columnNames){
		HashMap<String, Object> map=new HashMap<>();
		for(String columnName:columnNames)
		{
			Object str=InvokeSetterGetter.invokeGetter(loginModel.getBO(), columnName);
			if(str!=null)
			{
				map.put(columnName, str);	
			}
			System.out.println(str);
		}
		return BusinessObject.find(UserInfo.class,map);
	}

	public List<String> find(LoginModel loginModel){
		return BusinessObject.findAll(UserInfo.class);
	}
	public List<UserInfo> save(LoginModel loginModel){
		UserInfo userInfo=loginModel.getBO();
		BusinessObject.save(userInfo);
		HashMap<String, Object> map=new HashMap<>();
		map.put("username", loginModel.getBO().getUsername());
		map.put("password", loginModel.getBO().getPassword());
		return BusinessObject.find(UserInfo.class,map);
	}
}

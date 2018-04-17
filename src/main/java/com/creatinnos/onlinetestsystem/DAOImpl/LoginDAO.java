package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.daocustomization.ExecuteQuery;
import com.creatinnos.onlinetestsystem.model.LoginModel;

public class LoginDao {
	static Logger log = Logger.getLogger(JdbcTemplate.class.getName());
	
	ExecuteQuery executeQuery =new ExecuteQuery();
	public boolean find(String username,String password)
	{
		String findUser="select * from organization where username='"+username+"' and password='"+password+"';";
		return executeFetch(findUser);
	}
	
	public boolean register(LoginModel loginModel)
	{
		String query="insert into organization ("
				+"USERNAME ,PASSWORD ,EMAIL ,ORGANIZATIONNAME ,PHONENUMBER ,CREATEDDATE ,LASTMODIFIEDDATE) "
				+"VALUES ("
				+"'"+loginModel.getUserName()+"',"
				+"'"+loginModel.getPassword()+"',"
				+"'"+loginModel.getEmail()+"',"
				+"'"+loginModel.getCompanyName()+"',"
				+"'"+loginModel.getPhoneNumber()+"',"
				+"'"+(new Date())+"',"
				+"'"+(new Date())+"')";
		return executeQuery.executeInsert(query);
	}
	
	
	private static  boolean executeFetch(String query) {
		try {
			List<Map<String, Object>> maps=CreateConnection.getConnection().queryForList(query);
			
			if(maps!=null && maps.size()>0)
			{
				return true;
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return false;
	}
}

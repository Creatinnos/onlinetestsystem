package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.DAOImpl.LoginDAO;
import com.creatinnos.onlinetestsystem.bo.UserInfo;
import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.model.LoginModel;

@Path("/")
public class LoginService {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LoginService.class.getName());
	LoginDAO loginDAO=new LoginDAO();
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(LoginModel loginModel) {
		String output = "";
		
		List<UserInfo> userInfos=loginDAO.find(loginModel,UserInfo.USERNAME,UserInfo.PASSWORD);
		log.info(userInfos);
		if(userInfos!=null && userInfos.size()>0)
		{
			output = "Login Success";
		}
		else
		{
			output = "Login Failed";
		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/checUserExist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response checUserExist(LoginModel loginModel) {
		String output = "";
		List<UserInfo> userInfos=loginDAO.find(loginModel,UserInfo.USERNAME,UserInfo.PASSWORD);
		log.info(userInfos);
		if(userInfos!=null && userInfos.size()>0)
		{
			output = "Login Success";
		}
		else
		{
			output = "Login Failed";
		}
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response Register(LoginModel loginModel) {
		loginDAO.save(loginModel);
		return Response.status(200).entity("Success").build();
	}

}
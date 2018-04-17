package com.creatinnos.onlinetestsystem.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.DAOImpl.LoginDao;
import com.creatinnos.onlinetestsystem.model.LoginModel;

@Path("/")
public class LoginService {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LoginService.class.getName());
	LoginDao loginDAO=new LoginDao();
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginModel loginModel) {
		String output = "";
		boolean loginModels=loginDAO.find(loginModel.getUserName(),loginModel.getPassword());
		if(loginModels)
		{
			output = "{ \"ResponseMessage\" : \"Login Success\" }";
			log.info("Login Success --> "+loginModel);
		}
		else
		{
			output = "{ \"ResponseMessage\" : \"Login Failed\" }";
			log.error("Login Failed -->"+loginModel);
		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Register(LoginModel loginModel) {
		boolean loginModels=loginDAO.find(loginModel.getUserName(),loginModel.getPassword());
		String output = "";
		if(loginModels)
		{
			output = "{ \"ResponseMessage\" : \"User Already Exist\" }";
			log.error("User Already Exist --> "+loginModel);
		}
		else
		{
			loginDAO.register(loginModel);
			output = "{ \"ResponseMessage\" : \"Registered Successfully\" }";
			log.info("Registered Successfully --> "+loginModel);
		}
		return Response.status(200).entity(output).build();
	}

}
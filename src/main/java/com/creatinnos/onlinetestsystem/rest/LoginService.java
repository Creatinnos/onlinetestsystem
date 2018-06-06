package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.DAOImpl.LoginDao;
import com.creatinnos.onlinetestsystem.model.ResponseModel;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

@Path("/")
public class LoginService {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LoginService.class.getName());
	LoginDao loginDAO = new LoginDao();

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel login(OrganizationUsers organizationUsers) {
		List<OrganizationUsers> loginModels = loginDAO.findOrganization(organizationUsers.getUserName(), organizationUsers.getPassword());
		ResponseModel  responseModel=new ResponseModel(); 
		if (loginModels!=null && loginModels.size()>0) {
			log.info("Login Success --> " + organizationUsers);
			loginModels.get(0).setPassword(null);
			responseModel.setResponseMessage("Success");
			responseModel.setOrganizationUsers(loginModels.get(0));
		} 
		else {
			responseModel.setResponseMessage("Failure");
			responseModel.setOrganizationUsers(null);
			
		}
		
		return responseModel;
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrganizationUsers Register(OrganizationUsers organizationUsers) {
		List<OrganizationUsers> loginModels = loginDAO.findOrganization(organizationUsers.getUserName(),
				organizationUsers.getPassword());
		if (loginModels!=null && loginModels.size()>0) {
			log.error("User Already Exist --> " + organizationUsers);
			return null;
		} else {
			log.info("Registered Successfully --> " + organizationUsers);
			OrganizationUsers organizationUsers2=loginDAO.registerAdmin(organizationUsers).get(0);
			organizationUsers2.setPassword(null);
			return organizationUsers2;
		}
	}

}
package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.LoginDao;
import com.creatinnos.onlinetestsystem.model.ResponseModel;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

@Controller
public class LoginService {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LoginService.class.getName());
	LoginDao loginDAO = new LoginDao();

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseModel login(@RequestBody OrganizationUsers organizationUsers) {
		
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

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public OrganizationUsers Register(@RequestBody OrganizationUsers organizationUsers) {
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
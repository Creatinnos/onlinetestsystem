package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.UserDao;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

@Controller
public class UserService {

	@ResponseBody
	@RequestMapping(value = "/fetchAllUser", method = RequestMethod.GET )
	public List<OrganizationUsers> loadAllUser(@QueryParam("organizationId") String organizationId) {
		System.out.println(organizationId);
		UserDao userDAO = new UserDao();
		return userDAO.fetchUsers(organizationId);
	}

	
	@ResponseBody
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST )

	public OrganizationUsers saveUser(@RequestBody OrganizationUsers users) {
		UserDao userDAO = new UserDao();
		return userDAO.saveUser(users);
	}

	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST )

	public OrganizationUsers updateUser(@RequestBody OrganizationUsers users) {
		UserDao userDAO = new UserDao();
		if (userDAO.updateUser(users)) {
			System.out.println("save --> " + users);
			return users;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAllUser", method = RequestMethod.POST )

	public List<OrganizationUsers> updateUsers(@RequestBody List<OrganizationUsers> users) {
		UserDao userDAO = new UserDao();
		if (userDAO.updateUsers(users)) {
			System.out.println("save --> " + users);
			return users;
		}
		return null;
	}

}

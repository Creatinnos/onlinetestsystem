package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.creatinnos.onlinetestsystem.DAOImpl.UserDAO;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

@Path("/")
public class UserService {

	@GET
	@Path("/fetchAllUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrganizationUsers> loadAllUser(@QueryParam("organizationId") String organizationId) {
		System.out.println(organizationId);
		UserDAO userDAO = new UserDAO();
		return userDAO.fetchUsers(organizationId);
	}

	@POST
	@Path("/saveUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrganizationUsers saveUser(OrganizationUsers users) {
		UserDAO userDAO = new UserDAO();
		return userDAO.saveUser(users);
	}

	@POST
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrganizationUsers updateUser(OrganizationUsers users) {
		UserDAO userDAO = new UserDAO();
		if (userDAO.updateUser(users)) {
			System.out.println("save --> " + users);
			return users;
		}
		return null;
	}

	@POST
	@Path("/updateAllUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrganizationUsers> updateUsers(List<OrganizationUsers> users) {
		UserDAO userDAO = new UserDAO();
		if (userDAO.updateUsers(users)) {
			System.out.println("save --> " + users);
			return users;
		}
		return null;
	}

}

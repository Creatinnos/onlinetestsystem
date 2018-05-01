package com.creatinnos.onlinetestsystem.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.creatinnos.onlinetestsystem.DAOImpl.EventsDAO;
import com.creatinnos.onlinetestsystem.model.Events;

@Path("/")
public class EventService {

	@GET
	@Path("/fetchEvents")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Events fetchEvents() {
		EventsDAO eventsDAO=new EventsDAO();
		return eventsDAO.findEvents();
	}

}

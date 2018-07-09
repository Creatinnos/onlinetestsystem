package com.creatinnos.onlinetestsystem.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.EventsDao;
import com.creatinnos.onlinetestsystem.model.Events;

@Controller
public class EventService {

	@ResponseBody
	@RequestMapping(value = "/fetchEvents", method = RequestMethod.GET )
	public  Events fetchEvents() {
		EventsDao eventsDAO=new EventsDao();
		System.out.println("ds");
		return eventsDAO.findEvents();
	}

}

package com.creatinnos.onlinetestsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Events extends Token {
	private List<Event> events=new ArrayList<Event>();

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void addEvents(Event event) {
		if(this.events==null)
		{
			this.events=new ArrayList<Event>();
		}
		this.events.add(event);
	}



}

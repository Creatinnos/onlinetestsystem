package com.creatinnos.onlinetestsystem.model;

public class Event extends Token {
	
	private String event="";// : "RRB Practice Exam has been postponded. Exam schedule will be revealed shortly.", 
	private String postedBy="";//: "Sam Andrew", 
	private String postedOnDate="";// : "28/04/2018"},
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getPostedOnDate() {
		return postedOnDate;
	}
	public void setPostedOnDate(String postedOnDate) {
		this.postedOnDate = postedOnDate;
	}
	
}

package com.creatinnos.onlinetestsystem.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.creatinnos.onlinetestsystem.DAOImpl.ExamDao;
import com.creatinnos.onlinetestsystem.model.NewExam;

@Path("/exam")
public class ExamService {

	@POST
	@Path("/addExam")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addNewExam(NewExam newExam) {
		ExamDao dao=new ExamDao();
		dao.saveNewExam(newExam);
		System.out.println("consumeJSONList Client : " + newExam.toString());;
		return "yes";
	}

	@GET
	@Path("/fetchAllExam")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<NewExam> fetchAllExamList() {
		ExamDao dao=new ExamDao();
		return dao.fetchAllExam();
	}
	
	@GET
	@Path("/fetchExam")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<NewExam> fetchExamList(String examId) {
		ExamDao dao=new ExamDao();
		return dao.fetchExam(examId);
	}
}
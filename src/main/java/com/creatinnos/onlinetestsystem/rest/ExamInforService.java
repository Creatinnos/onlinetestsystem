package com.creatinnos.onlinetestsystem.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.creatinnos.onlinetestsystem.DAOImpl.ExamInfoDAO;
import com.creatinnos.onlinetestsystem.model.ExamInfos;

@Path("/")
public class ExamInforService {

	@GET
	@Path("/fetchExamInfos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ExamInfos fetchExamInfos() {
		ExamInfoDAO examInfoDAO=new ExamInfoDAO();
		return examInfoDAO.findExamInfos();
	}

}

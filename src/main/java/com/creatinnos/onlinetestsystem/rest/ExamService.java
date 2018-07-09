package com.creatinnos.onlinetestsystem.rest;

import java.util.ArrayList;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.ExamDao;
import com.creatinnos.onlinetestsystem.model.NewExam;

@Controller
public class ExamService {

	@ResponseBody
	@RequestMapping(value = "/saveExam", method = RequestMethod.POST )

	public NewExam addNewExam(@RequestBody NewExam newExam) {
		ExamDao dao=new ExamDao();
		dao.saveNewExam(newExam);
		System.out.println("consumeJSONList Client : " + newExam.toString());;
		return newExam;
	}

	@ResponseBody
	@RequestMapping(value = "/updateExam", method = RequestMethod.POST )

	public NewExam updateExam(@RequestBody NewExam newExam) {
		ExamDao dao=new ExamDao();
		dao.updateExam(newExam);
		System.out.println("consumeJSONList Client : " + newExam.toString());;
		return newExam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchAllExam", method = RequestMethod.GET )

	public ArrayList<NewExam> fetchAllExamList() {
		ExamDao dao=new ExamDao();
		return dao.fetchAllExam();
	}

	@ResponseBody
	@RequestMapping(value = "/fetchExam", method = RequestMethod.GET )

	public ArrayList<NewExam> fetchExamList(@PathParam("examId") String examId) {
		ExamDao dao=new ExamDao();
		return dao.fetchExam(examId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchOranizationExam", method = RequestMethod.GET )
	public ArrayList<NewExam> fetchOrganizationExamList( @RequestParam(required = false, value = "organizationId") String oranizationId,@RequestParam(required = false, value = "token") String token) {
		ExamDao dao=new ExamDao();
		return dao.fetchOrganizationExam(oranizationId);
	}
}
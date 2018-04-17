package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.creatinnos.onlinetestsystem.DAOImpl.QuestionDao;
import com.creatinnos.onlinetestsystem.model.Question;

@Path("/question")
public class UploadService {

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void consumeJSONsend(Question question) {
		QuestionDao questionDao=new QuestionDao();
		questionDao.saveQuestion(question);	
	}

	@POST
	@Path("/saveList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void consumeJSONList2(@RequestBody List<Question> questions) {
		for(int i=0;i<questions.size();i++)
		{
			QuestionDao questionDao=new QuestionDao();
			questionDao.saveQuestion(questions.get(i));	
		}
	}

	@GET
	@Path("/fetchQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> produceQuestions() {
		QuestionDao questionDao=new QuestionDao();
		List<Question> questions=questionDao.fetchQuestion();
		System.out.println("/fetchQuestion -->"+questions.toString());
		return questions;
	}

}
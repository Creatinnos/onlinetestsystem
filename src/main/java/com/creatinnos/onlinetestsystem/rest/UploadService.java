package com.creatinnos.onlinetestsystem.rest;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.model.Question;
import com.creatinnos.onlinetestsystem.utils.Utilities;

@Path("/question")
public class UploadService {

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void consumeJSONsend(Question question) {
		System.out.println("consumeJSONList Client : " + question.toString());;
		BusinessObject.save(question);
	}

	@POST
	@Path("/saveList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void consumeJSONList2(@RequestBody List<Question> clientList) {
		String output = clientList.size()+"consumeJSONList Client : " + clientList.toString() + "\n\n";
		System.out.println(output);
		for(int i=0;i<clientList.size();i++)
		{
			com.creatinnos.onlinetestsystem.bo.Question question=clientList.get(i).getBO();
			System.out.println(Utilities.questionBoToString(question));;
			BusinessObject.save(question);
		}
	}

	@GET
	@Path("/fetchQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String produceQuestions() {
		List<String> questions=BusinessObject.findAll(com.creatinnos.onlinetestsystem.bo.Question.class);
		System.out.println(questions.toString());
		return questions.toString();
	}

}
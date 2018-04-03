package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.creatinnos.onlinetestsystem.model.Answer;

@Path("/exam")
public class ExamService {

	@POST
	@Path("/saveAnswer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String consumeJSONsend(Answer answer) {
		System.out.println("consumeJSONList Client : " + answer.toString());;
		//BusinessObject.save(answer);
		return "yes";
	}

	@POST
	@Path("/saveAnswerList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void consumeJSONList2(@RequestBody List<Answer> answers) {
		String output = answers.size()+"consumeJSONList Client : " + answers.toString() + "\n\n";
		System.out.println(output);
		/*for(int i=0;i<answers.size();i++)
		{
			com.creatinnos.onlinetestsystem.bo.Question question=answers.get(i).getBO();
			System.out.println(Utilities.questionBoToString(question));;
			BusinessObject.save(question);
		}*/
	}
}
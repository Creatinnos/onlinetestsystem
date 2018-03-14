package com.creatinnos.onlinetestsystem.rest.sample;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.bo.Person;

@Component
@Path("/")
public class SampleRest {
	static int x = 0;

	@GET
	@Path("/hello")
	public Response savePayment() {

		System.out.println("sdfsd");
		return Response.status(200).entity("sfsd").build();

	}

	@POST
	@Path("/savequestions")
	public Response checkQuestions(@RequestBody String request) {
		String result ="Yes" + x;
		System.out.println(result+"requtBody"+request);
		x++;
		return Response.status(200).entity(result).build();
	}


	/*@RequestMapping(
			value="/savequestion",
			method=RequestMethod.POST
			)*/
	@POST
	@Path("/savequestion")
	public String savePerson(@RequestBody Person[] personArray) {
		List<String> response = new ArrayList<String>();
		System.out.println(personArray);
		if(personArray!=null)
		{
			/*for (Person person: personArray) {
				System.out.println("Saved person: " + person.toString());
				response.add("Saved person: " + person.toString());
			}*/
		}
		return "response";
	}

}
package com.creatinnos.onlinetestsystem.rest.sample;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client")
public class ClientService {

	@POST
	@Path("/sendList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consumeJSONList(List<ClientSamplePOJO> clientList) {

		String output = "consumeJSONList Client : " + clientList.toString() + "\n\n";
		System.out.println(output);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consumeJSONsend(ClientSamplePOJO client) {

		String output = "consumeJSONList Client : " + client.toString() + "\n\n";

		return Response.status(200).entity(output).build();
	}

}
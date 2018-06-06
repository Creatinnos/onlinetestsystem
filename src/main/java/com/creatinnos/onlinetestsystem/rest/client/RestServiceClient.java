package com.creatinnos.onlinetestsystem.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RestServiceClient {
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());
		/*List<Question> clientList = new ArrayList<Question>();
		clientList.add(new Question("type", "question", "choice1", "choice2", "choice3", "choice4",
				"choice5","choice6", "choice7", "choice8", "choice9", "choice10"));
		clientList.add(new Question("type", "question", "choice1", "choice2", "choice3", "choice4",
				"choice5","choice6", "choice7", "choice8", "choice9", "choice10"));
		clientList.add(new Question("type", "question", "choice1", "choice2", "choice3", "choice4",
				"choice5","choice6", "choice7", "choice8", "choice9", "choice10"));
		Response response = service.path("rest").path("question").path("receive").request().post(Entity.entity(clientList,MediaType.APPLICATION_JSON),Response.class);
		System.out.println("Form response " + response);*/
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:11958/onlinetestsystem/").build();
	}
}
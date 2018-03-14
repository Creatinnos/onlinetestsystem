package com.creatinnos.onlinetestsystem.rest.sample;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class ClientSamplePOJOClient {
  public static void main(String[] args) {
      ClientConfig config = new ClientConfig();
      Client client = ClientBuilder.newClient(config);
      WebTarget service = client.target(getBaseURI());
      List<ClientSamplePOJO> clientList = new ArrayList<ClientSamplePOJO>();
      clientList.add(new ClientSamplePOJO(1L, "pruebas@pruebas.com", "es"));
      clientList.add(new ClientSamplePOJO(2L, "pruebas@pruebas.com", "es"));
      clientList.add(new ClientSamplePOJO(3L, "pruebas@pruebas.com", "es"));
      Response response = service.path("rest").path("client").path("sendList").request().post(Entity.entity(clientList,MediaType.APPLICATION_JSON),Response.class);
      System.out.println("Form response " + response.getStatus());
  }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:11958/onlinetestsystem/").build();
  }
}
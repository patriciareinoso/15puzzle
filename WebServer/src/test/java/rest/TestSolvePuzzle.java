/**
This file is part of the course CSC5002.

Copyright (C) 2017 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Chantal Taconet
Contributor(s): Denis Conan &
		Oscar Guillen	

@author Chantal Taconet, Denis Conan & Oscar Guillen
 */

package rest;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Invocation;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Board;
import models.SolvingSequence;

public class TestSolvePuzzle {
	static  String REST_URI ;

	private static HttpServer server;
	private static WebTarget service;
	private static WebTarget service2;
	private static WebTarget service3;
	private static WebTarget helloService; //to test hello
	private static URI uri;
	private static URI helloUri; // to test hello

	@BeforeClass
	public static void setUp() throws Exception {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
        properties.load(input);
        REST_URI = "http://"+properties.getProperty("rest.serveraddress")+"/MyServer";
		// create a resource configuration that scans for JAX-RS resources and providers
		// in server package
		final ResourceConfig rc = new ResourceConfig().packages("server");
		// create and start a new instance of grizzly http server
		// exposing the Jersey application at REST_URI
		server = GrizzlyHttpServerFactory.createHttpServer(java.net.URI.create(REST_URI), rc);
		// start the server
		server.start();
		// create the client
		Client c = ClientBuilder.newClient();
		// uri and target to test hello
		helloUri = UriBuilder.fromUri(REST_URI+"/puzzle").build();
		helloService = c.target(helloUri);

		uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/linear").build();
		service = c.target(uri);

		uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/disorder").build();
		service2 = c.target(uri);
	
		uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/manhattan").build();
		service3 = c.target(uri);

		Thread.sleep(500);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		server.shutdownNow();
	}

	@Test
	public void test() {

		try {
			Invocation.Builder invocationBuilder = helloService.request(MediaType.TEXT_PLAIN);
			Response response = invocationBuilder.get();
			if (response.getStatus() == 200)
				System.out.println("Server online : " + response.readEntity(String.class));
			else
			{
				System.out.println("Server offline : " + response.getStatus());
				System.exit(9);
			}
		}
		catch ( Exception e)
		{ 
			System.out.println("Exception! => " + e.getMessage());
			System.exit(0);
		}

		String solution = "";
		String board = "0 2 3 4 1 5 6 8 9 11 7 12 13 10 14 15";
		
		System.out.println("\nTest 1\n");
		Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(board, MediaType.APPLICATION_XML),Response.class);
		
		System.out.println(response);

		if(response.getStatus() == 200) {
			solution = response.readEntity(String.class);
		}

		System.out.println("Solution: " + solution);
	
		System.out.println("#######################################");
		System.out.println("\nTest 2\n");
		invocationBuilder = service2.request(MediaType.APPLICATION_XML);
		response = invocationBuilder.post(Entity.entity(board, MediaType.APPLICATION_XML),Response.class);
		
		System.out.println(response);

		if(response.getStatus() == 200) {
			solution = response.readEntity(String.class);
		}

		System.out.println("Solution: " + solution);

		System.out.println("#######################################");
		System.out.println("\nTest 3\n");
		invocationBuilder = service3.request(MediaType.APPLICATION_XML);
		response = invocationBuilder.post(Entity.entity(board, MediaType.APPLICATION_XML),Response.class);
		
		System.out.println(response);

		if(response.getStatus() == 200) {
			solution = response.readEntity(String.class);
		}

		System.out.println("Solution: " + solution);
	}
}

/**
 * Client server to request service to the web server and solve puzzles.
 * 
 * @author Oscar Guillen
 *
 */

package client;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

import models.Board;
import models.SolvingSequence;
import models.Solution;

/**
 *  Client class
 */
public final class ClientServer {

	/**
	 * Http server.
	 */
	private static HttpServer server;
	/**
	 * Web Target to solving service
	 */
	private static WebTarget service;
	/**
	 * Web target to say hello to the server
	 */
	private static WebTarget helloService;
	/**
	 * URL to solving service
	 */
	private static URI uri;
	/**
	 * URL to hello service
	 */
	private static URI helloUri;
		/**
		 * the URI.
		 */
	public static  String REST_URI ;
	
	/**
	 * the default constructor is private to avoid instantiation.
	 */
	private ClientServer() {
	}

	/**
	 * the main method.
	 * 
	 * @param args
	 *            there is no command line arguments.
	 * @throws IOException 
	 */
	public static void main(final String[] args) throws IOException {

		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// create the client
			Client c = ClientBuilder.newClient();
			Properties properties = new Properties();
			FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
			properties.load(input);
			REST_URI = "http://"+properties.getProperty("rest.serveraddress")+"/MyServer";
			Client client = ClientBuilder.newClient();
			URI uri = UriBuilder.fromUri(REST_URI).build();
			WebTarget service = client.target(uri);

			// uri and target to test hello
			helloUri = UriBuilder.fromUri(REST_URI+"/puzzle").build();
			helloService = c.target(helloUri);
		
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
			String board = "";
			String heuristic = "";
			System.out.print("\nEnter puzzle [FORMAT: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0 where 0 is space and one space per number]: ");
			board = br.readLine();

			try {
				Board proof = new Board(board);
			}
			catch ( Exception e)
			{ 
				System.out.println("Exception! => " + e.getMessage());
				System.exit(0);
			}

			while(true) {
				System.out.print("\nEnter heuristic [OPTIONS: Linear Disorder Manhattan]: ");
				heuristic = br.readLine();
				if (heuristic.equals("Linear")) {
					uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/linear").build();
					service = c.target(uri);
					break;
				} else if (heuristic.equals("Disorder")) {
					uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/disorder").build();
					service = c.target(uri);
					break;
				} else if (heuristic.equals("Manhattan")) { 
					uri = UriBuilder.fromUri(REST_URI+"/puzzle/solve/manhattan").build();
					service = c.target(uri);
					break;
				} else
					System.out.println("Wrong answer. Try again.\n");
			}
		

			try {
				Solution proof2 = new Solution(heuristic);
			}
			catch ( Exception e)
			{ 
				System.out.println("Exception! => " + e.getMessage());
				System.exit(0);
			}
		
			System.out.println("\n\nLet's solve\n");
			Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
			Response response = invocationBuilder.post(Entity.entity(board, MediaType.APPLICATION_XML),Response.class);
		
			System.out.println(response);

			if(response.getStatus() == 200) {
				solution = response.readEntity(String.class);
			}

			System.out.println("\nWe are ready!\n\n\n");

			System.out.println("Solution: " + solution);

			System.out.println("---------------------------------------------------");

			System.out.println("---------------------------------------------------");

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}

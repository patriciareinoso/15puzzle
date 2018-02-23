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
 */
package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Board;
import models.Solution;
import models.SolvingSequence;

/**
 * The skiers REST server.
 */
@Path("/puzzle")
public final class SolutionRest {
	/**
	 * the file name of the data base.
	 */
	private String fileName = "src/main/resources/puzzle.bd";

	/**
	 * solve a puzzle using space position heuristic
	 * 
	 * @param line that represents a puzzle state
	 * @return the moves to reach the solution as a String
	 */
	@POST
	@Path("/solve/space")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	public String solveSpace(String line) throws JAXBException {
		Solution s1 = new Solution("Space");
		try {
		    Board start = new Board(line);
		    System.out.println("\nSpace Heuristic");
		    System.out.println("\nPuzzle to solve: \n" + start.toString());
		    s1.solve(start);
		    System.out.println("I found a path! " + s1.getSequence().toString());
		} catch (Exception e) {
			System.out.println("Exception cached: " + e.toString());
            e.printStackTrace();
		}
		return s1.getSequence().toString();
	}

	/**
	 * solve a puzzle using disorder heuristic
	 * 
	 * @param line that represents a puzzle state
	 * @return the moves to reach the solution as a String
	 */
	@POST
	@Path("/solve/disorder")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	public String solveDisorder(String line) throws JAXBException {
		Solution s1 = new Solution("Disorder");
		try {
			Board start = new Board(line);
			System.out.println("\nDisorder Heuristic");
			System.out.println("\nPuzzle to solve: \n" + start.toString());
		    s1.solve(start);
		    System.out.println("I found a path! " + s1.getSequence().toString());
		} catch (Exception e) {
			System.out.println("Exception cached: " + e.toString());
            e.printStackTrace();
		}
		return s1.getSequence().toString();
	}

	/**
	 * solve a puzzle using manhattan heuristic
	 * 
	 * @param line that represents a puzzle state
	 * @return the moves to reach the solution as a String
	 */
	@POST
	@Path("/solve/manhattan")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	public String solveManhattan(String line) throws JAXBException {
		Solution s1 = new Solution("Manhattan");
		try {
			Board start = new Board(line);
			System.out.println("\nManhattan Heuristic");
			System.out.println("\nPuzzle to solve: \n" + start.toString());
		    s1.solve(start);
		    System.out.println("I found a path! " + s1.getSequence().toString());
		} catch (Exception e) {
			System.out.println("Exception cached: " + e.toString());
            e.printStackTrace();
		}
		return s1.getSequence().toString();
	}

	/**
	 * simple hello get
	 * 
	 * @return hello message 
	 */

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		System.out.println("receiving a Hello");
		return "Server Puzzle Solver online, over...";
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Board exampleJSON() {
		
		return new Board();
	}
	
	@GET
	@Path("/sequence/xml")
	@Produces(MediaType.APPLICATION_XML)
	public SolvingSequence exampleSeqXML() {
		return new SolvingSequence();
	}
	
}

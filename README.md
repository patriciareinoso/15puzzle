# 15puzzle
Project for the course CSC7336: Software Engineering for Smart Devices.

## Project Description ##

The 15-puzzle is a puzzle game that consists of a board with numbered squared sliding tiles with the objective of placing them all in order.

The project was developed for the Advanced Software Engineering course, in a team of two people, following the best practices of software development. Three advanced software techniques were addressed in this project: device programming, artificial intelligences and web services. The details about the implementation and the decision making of the project are found in this report.

### Components ###

The **Models** module where is the solution of the puzzle solving problems. Here is all models needed to solve it and functions implementations. It is needed to use the other modules. 

The **Web Server** a REST service was created. It is in charge of solving puzzles, for this, it receives POST request with a string that represents a state of the board. Then, the intelligent algorithm is applied and the solving solution is sent back to the client. 

The **Android Application** having a solved puzzle, then it is possible to move tiles and request a solution. A Post request is made to connect to web server and get the solution. Then, the solution is received and simulated to the user.

## Installation and execution ##

It is possible to compile the whole project in the main directory:
	
	mvn clean install
	

Or let's compile per module. Compile Models module:
 
	cd Models
	mvn clean install
	cd ..

Compile Web server module. Here, the servers is tested:
 
	cd WebServer
	mvn clean install

Run Web Server:
 
	mvn exec:java@server

 * Open a web browser and prove the server requesting some services as
 
	http://localhost:8080/MyServer/puzzle
	or http://localhost:8080/MyServer/puzzle/json
	or http://localhost:8080/MyServer/puzzle/sequence/xml

Optionally, run a web client written in ClientServer.java to solve puzzles:

	mvn exec:java@client-server

Optionally, run an the android app on Android Studio. Notes: 
	- URL is fixed as: http://10.0.2.2:8080/. Because it is fixed to run on local web server.
	- It is needed to activate the web server before.
	- Manhattan heuristic service is used to request the solution.
	- Move tiles and tap ready.



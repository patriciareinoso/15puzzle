To compile and install, execute the following command:
$ mvn clean install

The services are first of all tested in JUnit tests.

Secondly, you can start a server and then some clients using two consoles.
In the first console, execute the following command:
$ mvn exec:java@server
...
Hit enter to stop it...
To stop the server, hit return in the first console.

Thirdly, the server can be tested using a Web browser.
In the console,  execute the following command:
$ mvn exec:java@server
...
Hit enter to stop it...
Then,test some of the services from a Web browser with those URLs
http://localhost:8080/MyServer/application.wadl
http://localhost:8080/MyServer/puzzle
http://localhost:8080/MyServer/puzzle/solve/space
http://localhost:8080/MyServer/puzzle/solve/manhattan
http://localhost:8080/MyServer/puzzle/solve/disorder
http://localhost:8080/MyServer/puzzle/sequence/xml
http://localhost:8080/MyServer/puzzle/json
To stop the server, hit return in the first console.

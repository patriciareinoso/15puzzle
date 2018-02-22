To compile and install, execute the following command:
$ mvn clean install

The services are first of all tested in JUnit tests.

Secondly, you can start a server and then some clients using two consoles.
In the first console, execute the following command:
$ mvn exec:java@server
...
Hit enter to stop it...
Then, in the second console, execute the following commands:
$ mvn exec:java@client-hello
$ mvn exec:java@client-calc
$ mvn exec:java@client-skier
To stop the server, hit return in the first console.

Thirdly, the server can be tested using a Web browser.
In the console,  execute the following command:
$ mvn exec:java@server
...
Hit enter to stop it...
Then,test some of the services from a Web browser with those URLs
http://localhost:9999/MyServer/application.wadl
http://localhost:9999/MyServer/calc/add?a=3&b=4
http://localhost:9999/MyServer/calc/sub?a=3&b=4
http://localhost:9999/MyServer/hello
http://localhost:9999/MyServer/hello/chantal
http://localhost:9999/MyServer/hello/replace?newmsg="Good morning"
http://localhost:9999/MyServer/skiers/all
http://localhost:9999/MyServer/skiers/alltxt
http://localhost:9999/MyServer/skiers/alljson
http://localhost:9999/MyServer/skiers?age=41
To stop the server, hit return in the first console.

You can also launch the client using the PHP program
$ php SkiersClient.php

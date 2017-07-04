# dropwizard-interview-questions
A sample/skeleton to serve as a starting off point for a new dropwizard service, it also serves as a repository for implementing some toy algorithms related to CS interview questions. 

This repositories only purpose is as a learning tool, none of algorithms should considered correct or optimal.

Blog post walking through this setup - http://honstain.com/new-dropwizard-1-0-5-java-service/
* Updated to dropwizzard 1.0.6

## Build and Run Locally
Building and running a basic dropwizard service as a JAR file.
We will be using maven-jar-plugin and maven-shade-plugin to construct a jar file with our required dependencies.
* http://www.dropwizard.io/1.0.5/docs/getting-started.html#running-your-application
```
mvn package
java -jar target/dropwizard-interview-questions-0.0.1-SNAPSHOT.jar server hello-world.yml
```

# Solutions
This is just a rough overview of the mish-mash of solutions coded up in here. *TAKE IT WITH A GRAIN OF SALT*, 
these are toy solutions created to help practice interviewing (getting interviewed and conducting interviews).
It is recorded here only for my own records, and it's usefulness for others is not really considered.

* Binary Tree - check if a binary tree is balanced.
 * Solution to CrackingTheCodingInterview problem 4.4 - solution produced on whiteboard under timelimit.
* Traverse a 2D array counter clockwise
 * Problem encountered in interview loop.
 

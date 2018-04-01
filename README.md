# dropwizard-interview-questions
A sample/skeleton to serve as a starting off point for a new dropwizard service, it also serves as a repository for implementing some toy algorithms related to CS interview questions. 

This repositories only purpose is as a learning tool, none of algorithms should considered correct or optimal.

Blog post walking through this setup - http://honstain.com/new-dropwizard-1-0-5-java-service/
* ~~Updated to dropwizzard 1.0.6~~
* Updated to dropwizard 1.2.4

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

* `HellowWorldResource` A grab bag of random algorithms implemented in the dropwizard resource
  * `uniqueCheck` Algorithm to check is a string has all unique characters
  * `uniqueCheckV2` Algorithm to check is a string has all unique characters
  * `permutationCompare` compare two strings identify if they are a permutation of each other
  * `permutationCompareV2` compare two strings identify if they are a permutation of each other
    * Example url localhost:8080/hello-world/permutationCompareV2?string1=AAAB&string2=ABAA
* `binaryTree` Binary Tree - check if a binary tree is balanced.
  * Solution to CrackingTheCodingInterview problem 4.4 - solution produced on whiteboard under timelimit.
* `HelloWorldResource.traverseCounterClockWise` Traverse a 2D array counter clockwise
  * Problem encountered in interview loop.
* `lrucache` Least Recently Used Cache
  * A toy implementation based on an interview question.
* `lrucacheV2` Least Recently Used Cache - Show stopping bug
  * A toy implementation based on an interview question, with a serious flaw.

# Insomnia Starter Config
https://insomnia.rest/ A http client to debug and experiment with the dropwizard API.
```json
{
	"_type": "export",
	"__export_format": 3,
	"__export_date": "2018-03-17T21:26:21.615Z",
	"__export_source": "insomnia.desktop.app:v5.14.9",
	"resources": [
		{
			"_id": "wrk_7b5c82f969224d428cac1adfd11c42fb",
			"created": 1521317545070,
			"description": "",
			"modified": 1521317545070,
			"name": "Insomnia",
			"parentId": null,
			"_type": "workspace"
		},
		{
			"_id": "env_c264721f22684cd5bf3a1ce27ab81b34",
			"color": null,
			"created": 1521317545235,
			"data": {},
			"isPrivate": false,
			"modified": 1521317545235,
			"name": "New Environment",
			"parentId": "wrk_7b5c82f969224d428cac1adfd11c42fb",
			"_type": "environment"
		},
		{
			"_id": "jar_709b6b109d914ae0ae7a555d3417af45",
			"cookies": [],
			"created": 1521317545327,
			"modified": 1521317545327,
			"name": "Default Jar",
			"parentId": "wrk_7b5c82f969224d428cac1adfd11c42fb",
			"_type": "cookie_jar"
		},
		{
			"_id": "req_0ac646c5a4814eec87e09029e9ce7ac1",
			"authentication": {},
			"body": {},
			"created": 1521317633643,
			"description": "",
			"headers": [],
			"metaSortKey": -1521317633644,
			"method": "GET",
			"modified": 1521321842816,
			"name": "Dropwizard Local",
			"parameters": [],
			"parentId": "wrk_7b5c82f969224d428cac1adfd11c42fb",
			"settingDisableRenderRequestBody": false,
			"settingEncodeUrl": true,
			"settingSendCookies": true,
			"settingStoreCookies": true,
			"url": "localhost:8080/hello-world/uniqueCheckV2/ABCDEFCG",
			"_type": "request"
		}
	]
}
```
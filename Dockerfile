FROM openjdk:8-jdk

COPY hello-world.yml /data/dropwizard-sample/hello-world.yml
COPY /target/dropwizard-sample-0.0.1-SNAPSHOT.jar /data/dropwizard-sample/dropwizard-sample-0.0.1-SNAPSHOT.jar

WORKDIR /data/dropwizard-sample

RUN java -version

CMD ["java","-jar","dropwizard-sample-0.0.1-SNAPSHOT.jar","server","hello-world.yml"]

EXPOSE 8080-8081
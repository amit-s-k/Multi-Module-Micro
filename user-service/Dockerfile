FROM openjdk:11
ADD ./target/microservice-0.0.1-SNAPSHOT.jar /usr/src/microservice-0.0.1-SNAPSHOT.jar
EXPOSE 8081
WORKDIR usr/src
ENTRYPOINT ["java","-jar","microservice-0.0.1-SNAPSHOT.jar"]




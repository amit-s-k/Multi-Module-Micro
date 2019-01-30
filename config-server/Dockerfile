FROM openjdk:11
ADD ./target/config-server-0.0.1-SNAPSHOT.jar /usr/src/config-server-0.0.1-SNAPSHOT.jar
EXPOSE 8888
WORKDIR usr/src
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]




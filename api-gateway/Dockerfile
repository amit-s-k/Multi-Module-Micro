FROM openjdk:11
ADD ./target/api-gateway-0.0.1-SNAPSHOT.jar /usr/src/api-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8085
WORKDIR usr/src
ENTRYPOINT ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]



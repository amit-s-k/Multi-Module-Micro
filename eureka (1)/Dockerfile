FROM openjdk:11
ADD ./target/eureka-0.0.1-SNAPSHOT.jar  /home/user/src/eureka-0.0.1-SNAPSHOT.jar
EXPOSE 8761
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","eureka-0.0.1-SNAPSHOT.jar"]
FROM openjdk:11
ADD ./target/music-0.0.1-SNAPSHOT.jar /usr/src/music-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR /usr/src
ENTRYPOINT ["java","-jar","music-0.0.1-SNAPSHOT.jar"]
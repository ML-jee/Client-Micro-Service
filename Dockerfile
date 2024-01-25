FROM openjdk:17
EXPOSE 8085
ADD target/ClientMicroService-0.0.1-SNAPSHOT.jar ClientMicroService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ClientMicroService-0.0.1-SNAPSHOT.jar"]
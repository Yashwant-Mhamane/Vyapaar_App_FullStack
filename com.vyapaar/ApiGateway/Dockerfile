FROM openjdk:17
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar appGateway.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "appGateway.jar"]
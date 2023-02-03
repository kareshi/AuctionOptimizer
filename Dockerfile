FROM openjdk:19-alpine
EXPOSE 8080
COPY target/my-maven-sample-app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
CMD ["java","-jar","/app.jar"]
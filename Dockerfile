FROM amazoncorretto:21-alpine
COPY target/api-1.0.0-SNAPSHOT.jar /app.jar
ENTRYPOINT [ "java", "-jar" , "/app.jar" ]
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV SERVER_PORT=8080
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--server.port=${SERVER_PORT}"]
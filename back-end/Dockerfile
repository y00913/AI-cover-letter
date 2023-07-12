FROM openjdk:17-jdk-slim
EXPOSE 8082
ARG JAR_FILE=./back-end/build/libs/back-end-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
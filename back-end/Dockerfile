FROM openjdk:17.0.1-jdk-slim
EXPOSE 8001
ARG JAR_FILE=./build/libs/back-end-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
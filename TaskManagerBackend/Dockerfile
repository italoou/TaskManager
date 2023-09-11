FROM openjdk:17-alpine
ARG JAR_FILE=target/TaskManagerBackend-0.0.2023-09-10.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8030

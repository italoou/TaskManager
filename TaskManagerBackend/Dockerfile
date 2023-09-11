# ### STAGE 1: Build ###
FROM maven:3.8.5-openjdk-17-slim AS java

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

### STAGE 2: Run ###
FROM openjdk:17-alpine

ARG JAR_FILE=/app/target/TaskManagerBackend-0.0.2023-09-10.jar

COPY --from=java ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8030

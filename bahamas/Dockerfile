FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src .

RUN mvn clean package

FROM openjdk:8-jdk-alpine

WORKDIR /app

ARG JAR_FILE=/build/target/*.jar

COPY --from=build ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

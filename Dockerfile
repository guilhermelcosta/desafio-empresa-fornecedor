FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY ../../.. .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build target/accenture-0.0.1-SNAPSHOT app.jar
COPY --from=build src/main/resources/application.properties application.properties

LABEL authors="Guilherme Costa"

ENTRYPOINT ["java", "-jar", "app.jar"]
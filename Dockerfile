FROM openjdk:17-buster

COPY sensitive.env /app/sensitive.env

ARG JAR_FILE=target/*.jar


COPY resources /app/resources


COPY ${JAR_FILE} /app/jira-1.0.jar

WORKDIR /app

# ENTRYPOINT команда
ENTRYPOINT ["java","-jar","jira-1.0.jar"]
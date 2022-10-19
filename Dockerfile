FROM postgres:13.1-alpine
ENV POSTGRES_USER compose-postgres
ENV POSTGRES_PASSWORD compose-postgres
ENV POSTGRES_DB compose-postgres

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} neoris-bank.jar
ENTRYPOINT ["java","-jar","/neoris-bank.jar"]
EXPOSE 8080
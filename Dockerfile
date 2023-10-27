FROM openjdk:17-jdk-slim-buster AS builder
EXPOSE 8080
WORKDIR /code
COPY . .
RUN ./gradlew clean
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "./build/libs/SalesPoint-0.0.1-SNAPSHOT.jar"]
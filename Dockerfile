FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir -p /app/logs

COPY ./build/libs/batch-0.0.1-SNAPSHOT.jar batch.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "batch.jar"]

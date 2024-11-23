FROM openjdk:23
LABEL authors="Abhishek Kandikattu"

EXPOSE 8080:8080

WORKDIR /app

COPY ./target/fetch-project-0.0.1-SNAPSHOT.jar /app/fetch-project-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "fetch-project-0.0.1-SNAPSHOT.jar"]




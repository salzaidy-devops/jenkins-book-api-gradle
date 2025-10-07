FROM eclipse-temurin:17-jre

LABEL authors="alzaidy"

WORKDIR /home/app

COPY build/libs/jenkins-book-api.jar book-api.jar


EXPOSE 8081

ENTRYPOINT ["java", "-jar", "book-api.jar"]
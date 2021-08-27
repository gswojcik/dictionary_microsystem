FROM openjdk:11-jre-slim-buster
COPY /target/DictionaryRestService-1.1.jar app/
WORKDIR app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DictionaryRestService-1.1.jar"]
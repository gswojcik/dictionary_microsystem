FROM openjdk:11-jre-slim-buster
COPY /target/DictionaryWebApp-1.1.jar app/
WORKDIR app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "DictionaryWebApp-1.1.jar"]
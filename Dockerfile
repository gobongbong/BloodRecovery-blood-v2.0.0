FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/*.jar
VOLUME /tmp
COPY ${JAR_FILE} app.jar
COPY blood-399111-0e0990f04325.json credential.json
ENV GOOGLE_APPLICATION_CREDENTIALS credential.json
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
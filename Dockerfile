FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/target/wst-backoffice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} wst-backoffice.jar
ENTRYPOINT ["java","-jar","wst-backoffice.jar"]
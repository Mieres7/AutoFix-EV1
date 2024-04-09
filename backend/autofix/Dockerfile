FROM openjdk:17
ARG JAR_FILE=target/autofix-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} autofix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/autofix-0.0.1-SNAPSHOT.jar"]
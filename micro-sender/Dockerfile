FROM openjdk:11-slim
EXPOSE 9000
ARG JAR_FILE=target/micro-sender-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
COPY ${JAR_FILE} app.jar
WORKDIR /app
#execute the application
ENTRYPOINT ["java","-jar","/app.jar"]
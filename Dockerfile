# Use the official OpenJDK 21 image as a base
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /kanban

# Copy the Maven build output (the JAR file) into the container
COPY target/kanban.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
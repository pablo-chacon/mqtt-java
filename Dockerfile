# Use an official OpenJDK runtime as a parent image (JDK for Maven).
FROM openjdk:11-jdk-slim

# Set the working directory in the container.
WORKDIR /app

# Copy the current directory contents into the container at /app.
COPY . /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Build the Java application, Maven no tests.
RUN mvn clean install -DskipTests


# Expose the MQTT broker port (adjust as necessary).
EXPOSE 1883

# Run the Java application.
CMD ["java", "-jar", "target/GpxLocationMqttApplication.jar"]

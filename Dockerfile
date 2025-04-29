FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8089

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# Step 1: Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies (to leverage Docker cache)
COPY pom.xml .

# Download all dependencies (skip tests) to cache the dependencies layer
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src /app/src

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Step 2: Create a minimal runtime image with the JAR file
FROM openjdk:17-jdk-slim

# Set the working directory in the runtime image
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/your-app.jar /app/your-app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/your-app.jar"]

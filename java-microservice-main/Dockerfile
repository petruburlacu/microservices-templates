# Multistage Dockerfile to build and run a Spring Boot application

# Stage 1: Build the application
FROM amazoncorretto:21-alpine-jdk AS build

# Set a working directory
WORKDIR /app

# Copy build-related files and the Gradle wrapper
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
# Copy the source code after resolving dependencies
COPY src ./src

# Ensure we have permissions to execute the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build -x test --no-daemon

# Stage 2: Create the final image with a slim Java 21 base image, should probably be JRE slim at this point
FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y curl
# Set a working directory for the final image
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

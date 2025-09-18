# ------------ Stage 1: Build the JAR using Maven + Java 17 ------------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies (use cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy the rest of the project
COPY . .

# Build the Spring Boot application (Blog Management)
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run the JAR using JDK 17 runtime ------------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built JAR file from previous stage
COPY --from=build /app/target/blog-management-0.0.1-SNAPSHOT.jar app.jar

# Expose port (adjust if you use a different one in application.properties)
EXPOSE 8080

# Run the Spring Boot Blog Management app
ENTRYPOINT ["java", "-jar", "app.jar"]

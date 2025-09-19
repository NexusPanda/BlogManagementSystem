# ------------ Step 1: Build stage ------------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Install CA certificates (fix PKIX path errors)
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Copy pom.xml first and download dependencies (cache friendly)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ------------ Step 2: Run stage ------------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Install curl + PostgreSQL client (for wait-for-db.sh)
RUN apt-get update && apt-get install -y curl postgresql-client && rm -rf /var/lib/apt/lists/*

# Add wait-for-db script
COPY wait-for-db.sh /usr/local/bin/wait-for-db.sh
RUN chmod +x /usr/local/bin/wait-for-db.sh

# Expose the local port (Render ignores this but keep consistent)
EXPOSE 4343

# ENTRYPOINT → wait for Render Postgres, then start app
ENTRYPOINT ["wait-for-db.sh", "dpg-d36dboffte5s73bc38h0-a.oregon-postgres.render.com", "5432", "--", "java", "-jar", "app.jar"]

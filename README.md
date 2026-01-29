# BlogManagementSystem

A RESTful Blog Management System built using **Spring Boot**, providing secure APIs for **user authentication**, **blog post management**, **comments**, and **categories/tags**.

The application follows **clean architecture principles**, uses **DTO mapping**, centralized **exception handling**, and integrates **PostgreSQL** for reliable data storage.

---

## 🚀 Features

* JWT-based Authentication & Authorization
* CRUD operations for Blogs, Comments, Categories
* Clean layered architecture (Controller, Service, Repository)
* PostgreSQL integration
* Dockerized & Cloud-deployable
* Production-ready configuration using environment variables

---

# 🧑‍💻 Local Development Setup

## 🛠️ Prerequisites

* Java 17+
* Maven or Gradle
* PostgreSQL (running locally)

---

## 📁 Configuration (Local)

The `application.properties` file is **intentionally ignored** for security reasons.

### ➤ Create configuration file

Path:

```
src/main/resources/application.properties
```

### ➤ Sample configuration

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Port
server.port=8080

# JWT
app.jwt.secret=your_secret_key
app.jwt.expiration=86400000
```

---

## ▶️ Run Locally

Using Maven:

```bash
./mvnw spring-boot:run
```

Or using Gradle:

```bash
./gradlew bootRun
```

Application will start at:

```
http://localhost:8080
```

---

# ☁️ Deployment Guide (Docker + Render)

This section explains how the application is deployed to **Render** using **Docker** and **PostgreSQL**.

---

## 🧱 Step 1: Build JAR File

From the project root:

```bash
./mvnw clean package
```

This generates:

```
target/BlogManagementSystem-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Step 2: Create Docker Image

### ➤ Dockerfile

Create a `Dockerfile` in the project root:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### ➤ Build Docker Image

```bash
docker build -t your-docker-username/blog-management-system .
```

---

## 📦 Step 3: Push Image to Docker Hub

### ➤ Login to Docker Hub

```bash
docker login
```

### ➤ Push Image

```bash
docker push your-docker-username/blog-management-system
```

---

## 🗄️ Step 4: Create PostgreSQL on Render

1. Go to **Render Dashboard**
2. Create **New → PostgreSQL**
3. Note down:

   * Database Name
   * Username
   * Password
   * **Internal Database URL**

⚠️ **Important**
Use **Internal Database URL** when connecting from a Render service (not External URL).

---

## 🚀 Step 5: Deploy on Render (Docker Image)

1. Create **New → Web Service**
2. Choose **Deploy an existing Docker image**
3. Provide Docker image:

   ```
   docker.io/your-docker-username/blog-management-system
   ```
4. Select:

   * Environment: **Docker**
   * Instance Type: Free / Starter

---

## 🔐 Step 6: Configure Environment Variables (Render)

Add the following environment variables in Render:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://<internal-host>:5432/<db_name>
SPRING_DATASOURCE_USERNAME=<db_username>
SPRING_DATASOURCE_PASSWORD=<db_password>

SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=false

APP_JWT_SECRET=your_secret_key
APP_JWT_EXPIRATION=86400000

SERVER_PORT=8080
```

✅ No `application.properties` is needed in production.

---

## 🔍 Notes on Logs & Authentication Errors

You may see logs like:

```
Unauthorized error: Full authentication is required to access this resource
```

This is **normal behavior** and indicates:

* Security filters are active
* Protected endpoints are accessed without JWT
* Your deployment is working correctly

---

## 🧠 Why This Deployment Approach?

* **Docker** ensures consistency across environments
* **Render** simplifies cloud deployment
* **Environment variables** protect sensitive data
* **PostgreSQL (managed)** ensures reliability and scalability

---

## 📌 Tech Stack

* Java 17
* Spring Boot
* Spring Security (JWT)
* PostgreSQL
* Docker
* Render
* Maven

---

## ✅ Best Practices Followed

* Secrets not committed to Git
* Clean architecture
* DTO-based API design
* Centralized exception handling
* Production-ready deployment

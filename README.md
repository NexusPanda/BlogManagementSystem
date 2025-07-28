# BlogManagementSystem

Built and implemented RESTful APIs for a blog management system using Spring Boot, enabling features such as user authentication, blog post management, commenting, and category/tag handling.

Integrated PostgreSQL for reliable data storage and retrieval, ensuring smooth operations for users, posts, and comments.

Followed clean architecture principles with DTO mapping and exception handling to ensure maintainability and scalability.

---

## 🛠️ Setup Instructions

### 🔧 Prerequisites
- Java 17+
- Maven or Gradle
- PostgreSQL (running and accessible)

### 📁 Configuration

This project requires a configuration file to run. The `application.properties` file is **intentionally ignored** in version control for security reasons.

#### ➤ Step 1: Create `application.properties`

Inside the folder:
```

src/main/resources/

```

Create a file named:

```

application.properties

````

#### ➤ Step 2: Add your configuration details

Here's a sample:

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
````

> You can also refer to the `application.properties.example` file included in the repo for reference.

---

## ▶️ Running the Application

After setting up your `application.properties`, you can run the project using:

```bash
./mvnw spring-boot:run
```

or

```bash
./gradlew bootRun
```

---

## ✅ Notes

* Do not commit your personal `application.properties` file to the repository.
* Use environment-specific files (`application-dev.properties`, etc.) for better separation.
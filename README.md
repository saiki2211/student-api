# Student API

A simple, production-ready **Spring Boot REST API** for managing student records. This project demonstrates best practices in Java backend development, including containerization, CI/CD, automated testing, API documentation, and monitoring.

---

## Features

- **CRUD API** for students (Create, Read, Update, Delete)
- **Spring Boot** with **Spring Data JPA**
- **H2 in-memory database** (auto-seeded with sample data)
- **OpenAPI/Swagger UI** for interactive API docs
- **Dockerized** for easy deployment
- **Jenkins pipeline** for CI/CD
- **Prometheus & Grafana** monitoring stack (via Docker Compose)
- **Automated tests** with JUnit and MockMvc

---

## Getting Started

### Prerequisites

- Java 17+ (recommended: Java 21)
- Maven 3.9+
- Docker (for containerization and monitoring stack)
- (Optional) Jenkins for CI/CD

---

### Running Locally

#### 1. Clone the repository

```bash
git clone https://github.com/your-username/student-api.git
cd student-api
```

#### 2. Build and run with Maven

```bash
./mvnw spring-boot:run
```

#### 3. Access the API

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **H2 Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa` (no password)

---

### Running with Docker

#### 1. Build the Docker image

```bash
docker build -t student-api .
```

#### 2. Run the container

```bash
docker run -p 8080:8080 student-api
```

---

### API Endpoints

| Method | Endpoint              | Description           |
|--------|----------------------|-----------------------|
| GET    | `/api/students`      | List all students     |
| POST   | `/api/students`      | Add a new student     |
| PUT    | `/api/students/{id}` | Update student by ID  |
| DELETE | `/api/students/{id}` | Delete student by ID  |

See [Swagger UI](http://localhost:8080/swagger-ui.html) for full details and to interact with the API.

---

### Database

- Uses **H2 in-memory** database by default.
- Preloaded with sample data from `src/main/resources/import.sql`.

---

### Testing

Run all tests:

```bash
./mvnw test
```

---

### CI/CD

- **Jenkinsfile** included for automated build, test, Docker image creation, and push to Docker Hub.

---

### Monitoring

A sample monitoring stack is provided using **Prometheus** and **Grafana**.

#### Start monitoring stack:

```bash
cd monitoring
docker-compose up
```

- **Prometheus:** [http://localhost:9090](http://localhost:9090)
- **Grafana:** [http://localhost:3000](http://localhost:3000)

---

## Project Structure

```
student-api/
  ├── src/main/java/com/example/student_api/   # Java source code
  ├── src/main/resources/                      # Properties, SQL, static files
  ├── src/test/java/com/example/student_api/   # Test classes
  ├── monitoring/                             # Prometheus & Grafana configs
  ├── Dockerfile                              # Docker build instructions
  ├── Jenkinsfile                             # Jenkins pipeline
  ├── pom.xml                                 # Maven config
  └── README.md                               # Project documentation
```

---

## License

This project is for educational purpose.

---

## Contact

For questions or contributions, open an issue or contact the maintainer.

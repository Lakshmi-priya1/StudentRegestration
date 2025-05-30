# Student Registration System

This is a full-stack Spring Boot application for managing student registrations. It provides RESTful APIs for admins and students to perform operations like registering, updating profiles, and viewing data. The application is Dockerized and integrated with a Jenkins-based CI/CD pipeline for automated builds, testing, and deployments.

## 🚀 Features

- Spring Boot REST APIs for admin and student operations
- JPA and Hibernate for database interaction
- Dockerized for platform-independent deployment
- CI/CD pipeline with Jenkins for automated builds and deployments
- Follows modern software engineering best practices

## 🛠️ Tech Stack

- Java 17  
- Spring Boot 3.4.4  
- Spring Data JPA & Hibernate  
- MySQL (via Docker)  
- Docker & Docker Compose  
- Jenkins (CI/CD)  
- GitHub for source control  

## 🗂️ Project Structure

student-registration/
├── src/
│ └── main/
│ ├── java/
│ │ └── com.example.studentregistration
│ └── resources/
│ ├── application.properties
│ └── static/
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
└── README.md

shell

## 🐳 Docker Setup

### 1. Build Docker Image

```bash
docker build -t student-registration-app:latest .
2. Run with Docker Compose
bash
docker-compose up --build
This will spin up both the Spring Boot app and the MySQL database.

🔁 CI/CD Pipeline
The Jenkinsfile in the root directory automates the following stages:

Checkout Code – Pulls latest code from GitHub

Build Jar – Compiles and builds the application

Build Docker Image – Packages the app into a Docker image

Start with Docker Compose – Runs the app and DB in containers

Test – Executes any unit/integration tests

Stop Containers – Cleans up Docker containers

Clean Workspace – Frees up Jenkins workspace post execution

📦 GitHub Repository

🔗 Student Registration GitHub Repo

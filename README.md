# task Management System Microservices

## 📌 Overview
A distributed Task Management System built with a microservices architecture, designed to handle task tracking, notifications, and collaboration (and in the future user authentication).
This project demonstrates real-world engineering practices, including service isolation, inter-service communication, and scalability.
The system is composed of multiple microservices communicating over REST and Kafka, and is designed for cloud deployment with Docker.

## 🚀 Features
✅ Task Service – create, update, delete, assign, and track tasks.

🔔 Notification Service –  manage users' notifications sent via Kafka.

🤝 Collaboration – team assignments.

📦 Scalability – containerized with Docker, CI/CD via Jenkins

📝 Tracing - Zipkin

API Documentation via OpenApi/Swagger

## 🏗️ Architecture

    
## ⚙️ Installation & Setup
1️⃣ Clone the repository
```
git clone https://github.com/Duru-DR/task-management-system-microservices.git
cd task-management-system-microservices
```

2️⃣ Start services with Docker Compose
```
docker-compose up --build
```

3️⃣ Access services

API Gateway → http://localhost:9090

Profile Service → http://localhost:8082 OR http://localhost:9090

Project Service → http://localhost:8083 OR http://localhost:9090

Task Service → http://localhost:8084 OR http://localhost:9090

Notification Service → http://localhost:8085 OR http://localhost:9090

Kafka-ui → http://localhost:8088

Zipkin → http://localhost:9411

Eureka Server → http://localhost:8761

## 🛠️ Tech Stack
Backend: Java 17, Spring Boot, Spring MVC, Spring Cloud

Database: PostgreSQL

Message Broker: Apache Kafka

API Gateway: Spring Cloud Gateway

Containerization: Docker, Docker Compose

Tracing: Zipkin

Database migrations through Flyway

## 📸 Screenshots

## 🧑‍💻 Author
Duru – www.linkedin.com/in/fatima-ezzahra-raqioui-08821b324

## ⭐ Future Improvements
Add user authentication (JWT, 2FA, OAuth)

Implement WebSocket notifications for real-time updates.

Add CI/CD deployment on AWS/GCP.

Add Front-end (Angular)


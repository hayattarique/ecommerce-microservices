# 🛒 E-Commerce Microservices Project

A modern **E-Commerce platform** built with **Spring Boot (Java 17)** and a microservices architecture.  
The system is designed for scalability, modularity, and clean separation of concerns — with an API Gateway, Authentication, Product, Mail, and Core services. In the future, an **Order Service** will be added to complete the e-commerce workflow.

---

## 🏗️ Architecture Overview

- **API Gateway (Spring Cloud Gateway)**  
  Entry point to the system. Handles routing and validates JWT tokens.

- **Auth Service**  
  Manages authentication & authorization. Issues JWTs for secure access.

- **Product Service**  
  Manages product catalog, inventory, and pricing.

- **Mail Service**  
  Handles email notifications (user registration, order confirmations, etc.).

- **Core Service**  
  Provides foundational business logic and interacts with persistence.

- **Future (Planned)**
    - **Order Service** → Cart management, order placement, payment integration.
    - **Payment Service** → Secure transaction handling.
    - **Inventory Service** → Stock and warehouse management.

---

## ⚙️ Technologies Used

- **Java 17** → Records used for DTOs and immutable responses
- **Spring Boot 3+**
- **Spring Cloud Gateway** → API Gateway & token validation
- **Spring Security + JWT** → Authentication & Authorization
- **Spring Data JPA** → Database persistence
- **Flyway** → Database migration/versioning
- **Spring Mail** → Email service
- **PostgreSQL/MySQL** (configurable database)

---

## ✨ Features

- ✅ Secure user authentication (JWT based)
- ✅ Centralized API Gateway with token validation
- ✅ Product catalog service with CRUD APIs
- ✅ Database schema managed with Flyway migrations
- ✅ Email notifications (SMTP)
- ✅ Uses **Java 17 records** for concise, immutable DTOs
- ✅ Ready for future extension (Order, Payment, Inventory services)

---

## 📂 Project Structure


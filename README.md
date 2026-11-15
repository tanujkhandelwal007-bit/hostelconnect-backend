# Hostel Connect (Backend)

Hostel Connect is a REST API backend that helps hostel students and hostel administration communicate in one place. Students can report hostel issues as complaints, and admins can post announcements, search complaints and view complaint analytics.

Access is protected with JWT authentication and role-based authorization (`STUDENT` and `ADMIN`).

---

## Features

- **Authentication**: register and log in, with BCrypt-hashed passwords and stateless JWT tokens.
- **Role-based access control**: separate `STUDENT` and `ADMIN` roles enforced with Spring Security (URL rules and `@PreAuthorize`).
- **Complaint management**: students raise complaints with room number, issue description and priority (`LOW`, `MEDIUM`, `HIGH`, `URGENT`). New complaints start as `OPEN`.
- **Admin complaint search**: filter complaints by status, priority, room number or student email.
- **Complaint analytics**: total, open, in-progress and resolved complaint counts for admins.
- **Announcements**: admins create, update and delete announcements; students and admins can read them.
- **Validation**: request bodies are validated (for example, valid email and required fields).
- **API docs**: Swagger / OpenAPI UI.

---

## Tech Stack

| Layer | Technology |
| :--- | :--- |
| Language | Java 17 |
| Framework | Spring Boot 3.5 (Web, Validation) |
| Security | Spring Security, JWT (jjwt 0.11.5), BCrypt |
| Database | MongoDB (Spring Data MongoDB) |
| API docs | springdoc-openapi (Swagger UI) |
| Build | Maven, Lombok |

---

## Project Structure

```
hostelconnect-backend/
└── hostelconnect/
    ├── pom.xml
    └── src/main/
        ├── java/com/hostelconnect/
        │   ├── HostelconnectApplication.java
        │   ├── controller/      # Auth, Student, Admin, Complaint, Announcement, User
        │   ├── service/         # ComplaintService, AnnouncementService, UserService
        │   ├── repository/      # MongoDB repositories
        │   ├── model/           # User, Complaint, Announcement, Role, Priority, ComplaintStatus
        │   ├── dtorequest/      # Request DTOs (validation)
        │   ├── dtoresponse/     # Response DTOs (analytics, auth, ApiResponse)
        │   └── security/        # SecurityConfig, JwtAuthFilter, JwtUtil
        └── resources/
            └── application.properties
```

---

## Getting Started

### Prerequisites

- Java 17
- Maven (or use the included wrapper)
- MongoDB running locally on port `27017`

### 1. Clone

```bash
git clone https://github.com/tanujkhandelwal007-bit/hostelconnect-backend.git
cd hostelconnect-backend/hostelconnect
```

### 2. Configure

Default settings in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/hostel_db
server.port=8080
```

Change the MongoDB URI if your database runs elsewhere.

### 3. Run

```bash
mvn spring-boot:run
```

The API runs at **http://localhost:8080**. Swagger UI: `http://localhost:8080/swagger-ui/index.html`.

---

## API Overview

Send the token from login as `Authorization: Bearer <token>` on protected endpoints.

### Auth (public)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/auth/register` | Register a user (defaults to `STUDENT`) |
| POST | `/auth/login` | Log in and receive a JWT |

### Student (`STUDENT` role)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/student/dashboard` | Student dashboard check |
| POST | `/student/complaints` | Raise a complaint |

### Admin (`ADMIN` role)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/admin/analytics` | Complaint counts by status |
| GET | `/admin/complaints/search` | Search by `status`, `priority`, `roomNumber`, `studentEmail` |

### Announcements

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| POST | `/api/announcements` | Admin | Create an announcement |
| GET | `/api/announcements` | Admin, Student | List announcements |
| GET | `/api/announcements/{id}` | Admin, Student | Get one announcement |
| PUT | `/api/announcements/{id}` | Admin | Update an announcement |
| DELETE | `/api/announcements/{id}` | Admin | Delete an announcement |

**Example: register and raise a complaint**

```json
// POST /auth/register
{ "name": "Rahul", "email": "rahul@example.com", "password": "secret123", "roomNumber": "B-204" }

// POST /student/complaints
{ "studentEmail": "rahul@example.com", "roomNumber": "B-204", "issue": "Water leakage in bathroom", "priority": "HIGH" }
```

---

## Roadmap

- [ ] Marketplace module for students to buy and sell pre-owned items
- [ ] Endpoint for admins to update complaint status (`OPEN` → `IN_PROGRESS` → `RESOLVED`)
- [ ] Students can view their own complaints
- [ ] Move the JWT secret and settings to environment variables
- [ ] Global exception handling and consistent API responses
- [ ] Unit and integration tests
- [ ] Docker support

---

## Author

**Tanuj Khandelwal**
GitHub: [@tanujkhandelwal007-bit](https://github.com/tanujkhandelwal007-bit)

# E-Commerce Backend (Spring Boot)

A RESTful e-commerce backend built with **Java, Spring Boot and MySQL**. It covers user authentication, product management, cart and order processing.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Maven](https://img.shields.io/badge/Build-Maven-red)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)


## Features
- User registration and login with **Spring Security + JWT**
- Role-based access control (`ADMIN` / `USER`)
- Product and category management (CRUD)
- Search, filter and pagination for products
- Shopping cart management
- Order placement and order history
- Request validation with Bean Validation
- Global exception handling with clean error responses
- API documentation with Swagger / OpenAPI

## Tech Stack
| Category | Technology |
|----------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security, JWT |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL 8 |
| Build Tool | Maven |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Testing | JUnit 5, Mockito |

## Architecture
The project follows a layered architecture:

```
Client → Controller → Service → Repository → MySQL
```

- **Controller**: handles HTTP requests and responses
- **Service**: business logic
- **Repository**: database access via Spring Data JPA
- **DTO/Entity**: separates API models from database models

## Project Structure
```
ecommerce/
├── src/main/java/com/example/ecommerce/
│   ├── config/          # Security and app configuration
│   ├── controller/      # REST controllers
│   ├── dto/             # Request/response objects
│   ├── entity/          # JPA entities
│   ├── exception/       # Custom exceptions and global handler
│   ├── repository/      # Spring Data repositories
│   ├── security/        # JWT filter and utilities
│   ├── service/         # Business logic
│   └── EcommerceApplication.java
├── src/main/resources/
│   └── application.properties
├── src/test/java/       # Unit and integration tests
├── pom.xml
└── README.md
```
> Adjust the package names to match your project.

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.8+
- MySQL 8.0+
- Git

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/abishek21092001-maker/ecommerce.git
cd ecommerce
```

**2. Create the database**
```sql
CREATE DATABASE ecommerce_db;
```

**3. Configure the application**

Update `src/main/resources/application.properties` (see [Configuration](#configuration)).

**4. Build and run**
```bash
mvn clean install
mvn spring-boot:run
```

The server starts at **http://localhost:8080**.

Swagger UI: **http://localhost:8080/swagger-ui/index.html**

## Configuration
`src/main/resources/application.properties`:

```properties
server.port=8080

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT
jwt.secret=your_secret_key_at_least_32_characters_long
jwt.expiration=86400000
```

> **Warning:** Never commit real passwords or secrets. Use environment variables or an untracked `application-local.properties`.

## API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/register` | Register a new user | Public |
| POST | `/api/auth/login` | Login and receive JWT | Public |

### Products
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/products` | Get all products (pagination supported) | Public |
| GET | `/api/products/{id}` | Get product by ID | Public |
| POST | `/api/products` | Add a product | Admin |
| PUT | `/api/products/{id}` | Update a product | Admin |
| DELETE | `/api/products/{id}` | Delete a product | Admin |

### Cart
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/cart` | View cart | User |
| POST | `/api/cart/add` | Add item to cart | User |
| DELETE | `/api/cart/remove/{itemId}` | Remove item | User |

### Orders
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/orders` | Place an order | User |
| GET | `/api/orders` | Get my orders | User |
| GET | `/api/admin/orders` | Get all orders | Admin |
| PUT | `/api/orders/{id}/status` | Update order status | Admin |

### Sample Requests

**Register**
```json
POST /api/auth/register
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "Password@123"
}
```

**Login response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer"
}
```

**Authenticated request**
```bash
curl -H "Authorization: Bearer <your_token>" http://localhost:8080/api/orders
```

## Database Schema
Main entities and relationships:

```
User ──< Order ──< OrderItem >── Product >── Category
User ──── Cart ──< CartItem  >── Product
```

| Table | Description |
|-------|-------------|
| `users` | Customer and admin accounts |
| `products` | Product details, price, stock |
| `categories` | Product categories |
| `carts` / `cart_items` | Shopping cart data |
| `orders` / `order_items` | Placed orders and their items |

## Testing
```bash
mvn test
```

## Future Improvements
- [ ] Payment gateway integration (Razorpay / Stripe)
- [ ] Email notifications for orders
- [ ] Product reviews and ratings
- [ ] Docker and docker-compose setup
- [ ] CI/CD with GitHub Actions
- [ ] Redis caching

## Contributing
1. Fork the repository
2. Create a branch: `git checkout -b feature/your-feature`
3. Commit: `git commit -m "Add your feature"`
4. Push: `git push origin feature/your-feature`
5. Open a Pull Request

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Author
**Abishek**
GitHub: [@abishekdharamaraj-maker](https://github.com/abishekdharamaraj-maker)

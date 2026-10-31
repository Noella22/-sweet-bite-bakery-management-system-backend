# SweetBite Bakery Management System

**Student Name:** TWIRINGIYIMANA Noella  
**Student ID:** 26749  
**Course:** INSY 8322  Web Technology and Internet  
**Project:** SweetBite Bakery Management System  
**University:** Adventist University of Central Africa (AUCA)

## Project Description

This project is a backend system for managing the daily activities of a bakery. I developed it using Java, Spring Boot, Spring Data JPA, and PostgreSQL.

The system manages customers, bakers, ovens, baking skills, cake orders, and payments.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Postman
- Visual Studio Code
- Git and GitHub

## Features Implemented

**Part A – CRUD Operations**

I implemented CRUD operations to create, view, update, and delete bakery records.

**Part B – Derived Queries**

I used Spring Data JPA derived methods to retrieve records from the database.

**Part C – JPQL Queries**

I implemented queries to find bakers by baking skill, free ovens, unused baking skills, and customers assigned to a baker.

**Part D – Business Rules**

I added validation for cake orders, order status changes, assigning baking skills to bakers, and changing a baker's oven.

**Part E – Payments**

I implemented payment recording for ready cake orders, searching payments by customer code, and deletion rules.

**Part F – Reports and Bulk Updates**

I implemented cake order statistics by status and cancellation of eligible orders for a baker on a given date.

**Bonus Features**

- Dashboard summary showing customers, bakers, free ovens, today's orders, placed orders, and payments.
- Pagination and sorting for cake orders.

## How to Run the Project

1. Install Java 17 and PostgreSQL.
2. Open the project in Visual Studio Code.
3. Create a PostgreSQL database and configure the database connection in `src/main/resources/application.properties`.
4. Open the terminal in the main project folder.
5. Run:

```powershell
.\mvnw.cmd spring-boot:run
```

6. Once the application starts, test the endpoints in Postman using:

`http://localhost:8080`

## Testing

I used Postman to test the API endpoints, including successful requests and error responses.

The exported Postman collection is included in the project repository.

## GitHub Repository

https://github.com/Noella22/-sweet-bite-bakery-management-system-backend.git
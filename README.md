
# Task Management System

This Application is created as a backend exercise for Banque Misr by Mohamed Ahmed Bahaa Elbakly.  

 A backend web application for managing tasks, built with Spring 
 Boot.
 
  The system provides REST APIs for CRUD operations, user authentication, and task filtering/searching, ensuring data integrity and role-based access control

## Features
- **Task Management**:
  - CRUD operations for tasks:    Create, Read, Update, Delete.
  - Tasks include attributes like title, description, status, priority,assignedTo, and due date.

- **Authentication and Authorization**:
  - JWT-based authentication using Spring Security.
  - Role-based access control with `ADMIN` and `USER` roles.

- **Search and Filtering**:
  - Search the tasks by title
  - Filter tasks based on any task criteria.

- **Data Validation and Error Handling**:
  - Input validation for all endpoints.
  - error handling with custom messages.

- **Email Notifications** :
  - Email notifications for users once they receive a new task.

- **Performance Optimization**:
  - Pagination for tasks and history lists to handle large datasets.

  **For a detailed explanation of the exercise checklist and additional notes on testing the application please refer to the [Closing Notes](#closing-notes) section.**



## Technologies Used

- **Java 17**:    Programming language.

- **IntelliJ IDEA 2024.2.3 (Community Edition)**: IDE for java.

- **Spring Boot 3.4.0**: Framework for building RESTful APIs.

- **Spring Security**: For JWT-based authentication and role-based access control.

- **Hibernate/JPA**: ORM for database interactions.

- **Maven**: Build and dependency management tool.

- **Lombok**: Reduces boilerplate code for entities and services.

- **jjwt (JWT API)**: For generating and validating JSON Web Tokens.

- **H2 Database**: In-memory database for development and testing.

- **Spring Boot Starter Validation**: For input validation.

- **Spring Boot Starter Mail**: For sending email notifications.

- **Spring Boot Starter Logging**: For console logs in the application.
- **Postman**: API for testing.


## Setup Instructions

**IMPORTANT NOTES:**

- **The database used in this project is H2-Database along with Hibernate for database interactions, so there is no need to run any SQL create queries for the tables. They will be automatically generated.**

- **The H2-Database resets every time the application is run, so seed data for the tables has been created (see the `data.sql` file in the project) to ease testing the application.**

To test this project, simply follow these steps:

1. **Clone the repository**:
   - Clone the repository to your local machine using Git or CMD prompt command in a folder:
     ```bash
     git clone https://github.com/MohamedBahaa001/banquemisr.challenge05.git
     ```
   **Or Just Download the project zip from https://github.com/MohamedBahaa001/banquemisr.challenge05.git - press on code - download the zip file- extract it**

2. **Open the project in IntelliJ**:
   - Open the project using IntelliJ IDEA.

3. **Run the application**:
   - Navigate to `src -> main -> java -> TaskApplication.java` in your project.
   - Run the `TaskApplication.java` file to start the application.
   - The application will be running by default on `http://localhost:8080`.

4. **Database**:
   - The H2 database is configured by default, so no further configurations are needed.
   - You can view the H2 database console at `http://localhost:8080/h2-console` after running the application.
   - To access the database, use the following credentials (these can also be found in the `application.properties` file):
     - `Driver Class`: `org.h2.Driver`
     - `URL`: `jdbc:h2:mem:taskdb`
     - `User`: `mohamed`
     - `Password`: (leave blank, no password)
   - When connected to the database, you should notice 3 tables on the left. These are the seed tables. Click on them and run the query to view the seed data.

5. **Test the API**:
   - The project should be successfully configured by this step.
   - Use Postman to test the API endpoints mentioned in the `API Endpoints` section of the `README.md`.

## API Endpoints

**IMPORTANT:** 

**For the sake of simplicity in testing, multiple seed data were set to be inserted upon running the application.**

**For Example: Database will already have an ADMIN and a USER with the following credentials:**

**Default Admin:**
- **Username**: admin
- **Password**: 12345 (hashed in the database)
- **Email**: ataskmanagementsystem@gmail.com (used to send real emails)

**Default User:**
- **Username**: mohamed
- **Password**: 12345 (hashed in the database)
- **Email**: mohamedspring@yopmail.com (can be viewed from www.yopmail.com by inserting the name "mohamedspring")

**Default user and admin will have their JWT token printed in the console for reference ,however, you can always authenticate for a new token.**

**Seed users (and database entries for these users) were created to ease testing all the APIs, however, feel free to test and add everything from scratch.**

The project is set to run on default: `http://localhost:8080`

**IMPORTANT:** 

---

### Authentication
**Data Types:**
- **username**: "String"
- **password**: "String"
- **email**: "String"
  
| Method | Endpoint                  | Description                            | Parameters                               |
|--------|---------------------------|----------------------------------------|------------------------------------------|
| POST   | `/api/auth/authenticate`          | Authenticate user (Sign in) and get JWT token.   | `username`, `password` (Required JSON Body)        |
| POST   | `/api/auth/registerUser`       | Register a new user and get JWT.        | `username`, `password`, `email` (Required JSON Body) |
| POST   | `/api/auth/registerAdmin`       | Register a new admin and get JWT         | `username`, `password`, `email` (Required JSON Body) |

---

### Task Management (Admin)
**Data Types:**
- **title**: "String"
- **description**: "String"
- **status**: "Enum" ("TODO", "IN_PROGRESS", "DONE")
- **priority**: "Enum" ("HIGH", "MEDIUM", "LOW")
- **assignedTo**: "String" (Username of the user to whom the task is assigned)
- **dueDate**: "LocalDate" (String format: "yyyy-MM-dd")
- **page**: "Integer"
- **size**: "Integer"

| Method | Endpoint                    | Description                               | Parameters                                               |
|--------|-----------------------------|-------------------------------------------|----------------------------------------------------------|
| POST   | `/api/admin/tasks/create`          | Create a new task.                        | `title`, `description`, `status`, `priority`, `assignedTo`, `dueDate` (Required JSON body) |
| GET    | `/api/admin/tasks/getAll`          | Retrieve all tasks with pagination.       | `page`, `size` (Optional query parameters for pagination)                  |
| GET    | `/api/admin/tasks/getTaskById/{id}`     | Get a task by its ID.                     | `id` (Required path parameter)                                          |
| PUT    | `/api/admin/tasks/update/{id}`     | Update an existing task by id and new task body.                  | `id`(Required Path Parameter), `title`, `description`, `status`, `priority`, `dueDate`, `assignedTo` (Required JSON body) |
| DELETE | `/api/admin/tasks/delete/{id}`     | Delete a task by id.                            | `id` (Required Path Parameter)                                          |
| GET    | `/api/admin/tasks/filter`     | Filter tasks table by any parameter                             | `title`, `description`, `status`, `priority`, `dueDate`, `assignedTo` (Optional Query parameters), `page`, `size` (Optional query parameters for pagination) |
| GET    | `/api/admin/tasks/searchByTitle/`     | Search tasks by title or partial title.                             | `title` (Required query parameter), `page`, `size` (Optional query parameters for pagination) |

---

### Task Management (User)
**Data Types:**
- **taskId**: "Long"
- **title**: "String"
- **description**: "String"
- **status**: "Enum" ("TODO", "IN_PROGRESS", "DONE")
- **priority**: "Enum" ("HIGH", "MEDIUM", "LOW")
- **dueDate**: "LocalDate" (String format: "yyyy-MM-dd")
- **page**: "Integer"
- **size**: "Integer"

| Method | Endpoint                     | Description                                  | Parameters                                                                                     |
|--------|------------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------|
| GET    | `/api/user/tasks/myTasks`    | Retrieve tasks assigned to the authenticated user. | `page`, `size` (Optional query parameters for pagination) |
| GET    | `/api/user/tasks/searchByTitle` | Search user's tasks by title or partial title. | `title` (Required query parameter), `page`, `size` (Optional query parameters for pagination) |
| PUT    | `/api/user/tasks/updateStatus` | Update the status of a task assigned to the user. |  `taskId`,   `status`  (Required query parameters) |
| GET    | `/api/user/tasks/filter`     | Filter tasks based on any criteria assigned to the user. | `title`, `description`, `status`, `priority`, `dueDate` (Optional query parameters), `page`, `size` (Optional query parameters for pagination) |

- **NOTE**: When a task status is updated to DONE it gets removed from the usertasks and moved to the tasks history table (History APIs below)

---

### History (Admin and User)
**Data Types:**
- **page**: "Integer"
- **size**: "Integer"

| Method | Endpoint                         | Description                                      | Parameters                           |
|--------|----------------------------------|--------------------------------------------------|--------------------------------------|
| GET    | `/api/history/admin/allHistory`  | Fetch history table (Admin Only).                | `page`, `size` (Optional for pagination) |
| GET    | `/api/history/user/myHistory`    | Fetch history of tasks assigned to user (User Only).     | `page`, `size` (Optional for pagination) |

---

### Email Notifications (Admin)
**Data Types:**
- **title**: "String"
- **description**: "String"
- **assignedTo**: "String" (Username of the assigned user)
- **status**: "Enum" ("TODO", "IN_PROGRESS", "DONE")
- **priority**: "Enum" ("HIGH", "MEDIUM", "LOW")
- **dueDate**: "LocalDate" (String format: "yyyy-MM-dd")

| Method | Endpoint                                 | Description                                          | Parameters                                                |
|--------|------------------------------------------|------------------------------------------------------|-----------------------------------------------------------|
| POST   | `/api/email/admin/createTaskAndNotifyByEmail` | Create a new task and notify the assigned user via email. | `title`, `description`, `assignedTo`, `status`, `priority`, `dueDate` (Required JSON Body) |

---
## Closing Notes

- The application was tested by me (Mohamed Elbakly) on all APIs specified in the API Endpoints section using Postman.  
- 25 database entries for tasks were initially inserted for testing (`data.sql` file in resources folder), 5 of which are for the default user "mohamed".
- 5 History entries were initially inserted, 3 of which are for the default user "mohamed". 
  
- The application covers all the basic requirements for the project plus two bonuses: Email Notifications and Pagination.
- APIs are handled with suitable http status responses to be returned to user.
- The application is implemented using the MVC Design Pattern
- The `application.properties` file for all application configurations is included in the project. (Normally it should be added to gitignore, it was left to ease testing on the tester).



 Task Requirments Checklist with additional notes:

### Task Management APIs
CRUD operations for managing tasks (create, read, update, delete) are fully implemented in the application.

### Authentication and Authorization
Security for the project is managed using Spring Security with JWT authentication. Users must have specific permissions (ROLE) to access endpoints; otherwise, they will receive a **403 Forbidden** HTTP error.  

**Note:** Testing any API after `/authenticate` requires a bearer token (JWT). This token is provided as a JSON response after authentication/registration and must be included in the Authorization - Bearer Token field for accessing User/Admin APIs.

### Validation and Error Handling
Input validation is implemented with descriptive error messages for task and user creation.

### Search and Filtering
Tasks can be searched and filtered by specific criteria, providing flexibility for task management.

### Bonuses
 - **Email Notifications:**  
   Email notifications are configured using Gmail SMTP. A dedicated Gmail account was created for the default admin user to handle task-related emails.  

 - **Pagination:**  
   Pagination is implemented using Spring's `Pageable` type. The APIs allow manual adjustment of the `page` and `size` parameters.  
   By default, paging starts from page 0 with a maximum size of 20 elements per page. For example, this API request fetches elements on the second page (page 1) with a maximum size of 10 per page (elements 11-20):  
   `"http://localhost:8080/api/admin/tasks/getAll?page=1&size=10"`

---


Finally, thank you for taking the time to review my backend exercise.
 I look forward to your feedback and hope this implementation meets your    expectations.

**Best Regards,**  
**Mohamed Elbakly**  

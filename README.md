# Student Management System

## Overview

Student Management System is a Java-based desktop application built using a client-server architecture. The application provides a Java Swing graphical user interface for managing student records and communicates with a Java server through socket programming.

The server handles client requests, processes student operations, and interacts with a MySQL database using JDBC. The project stores student data in a MySQL database named `studentdb`, with records maintained in the `students` table.

Screenshots and architecture images are stored separately inside the `docs/` folder. They are not embedded in this README.

## Features

| Feature | Description |
|----------|-------------|
| Add Student | Insert new student records |
| View Students | Display all student records |
| Search Student | Find students by ID |
| Update Student | Modify existing records |
| Delete Student | Remove student records |
| Client-Server Communication | Socket-based communication |

## Tech Stack

- Java
- Java Swing
- Java Socket Programming
- JDBC
- MySQL
- MySQL Connector/J

## System Architecture

The project follows a client-server architecture.

Architecture flow:

```text
StudentClient Java Swing GUI
    -> StudentServer
    -> ClientHandler
    -> DBConnection using JDBC
    -> MySQL database
```

The `StudentClient` class provides the desktop user interface and sends requests to the server over a socket connection. The `StudentServer` listens for incoming client connections and assigns each request to `ClientHandler`. The `ClientHandler` processes student operations such as add, view, search, update, and delete. Database access is handled through `DBConnection`, which uses JDBC to connect to the MySQL database.

## Project Structure

```text
StudentManagementSystem/
│
├── .github/
├── .vscode/
│   └── settings.json
│
├── docs/
│   ├── architecture/
│   │   ├── system-architecture.md
│   │   └── system-architecture.png
│   │
│   └── screenshots/
│
├── lib/
│
├── src/
│   ├── client/
│   │   └── StudentClient.java
│   │
│   ├── db/
│   │   └── DBConnection.java
│   │
│   ├── model/
│   │   └── Student.java
│   │
│   └── server/
│       ├── ClientHandler.java
│       └── StudentServer.java
│
├── .gitignore
└── README.md
```

## Database Setup

Create the MySQL database:

```sql
CREATE DATABASE studentdb;
```

Use the database:

```sql
USE studentdb;
```

Create the `students` table:

```sql
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    branch VARCHAR(100) NOT NULL,
    marks DOUBLE NOT NULL
);
```

## How to Run

Make sure MySQL is running and the `studentdb` database has been created before starting the application.

Compile the project:

```bash
javac -cp "lib/*" -d out src/client/*.java src/server/*.java src/db/*.java src/model/*.java
```

Run the server first:

```bash
java -cp "out;lib/*" server.StudentServer
```

Then run the client in a separate terminal:

```bash
java -cp "out;lib/*" client.StudentClient
```

For macOS or Linux, use `:` instead of `;` in the classpath:

```bash
java -cp "out:lib/*" server.StudentServer
java -cp "out:lib/*" client.StudentClient
```

## Configuration

Update your own MySQL username and password in:

```text
src/db/DBConnection.java
```

Use your local MySQL credentials in the `USER` and `PASSWORD` fields. Do not commit real passwords or sensitive configuration values to a public repository.

The database name used by the project is:

```text
studentdb
```

The main table used by the project is:

```text
students
```

Table columns:

```text
id
name
branch
marks
```

## Author

Ishaan Sharma

- GitHub: [ishaans04](https://github.com/ishaans04)
- LinkedIn: [Ishaan Sharma](https://www.linkedin.com/in/ishaan-sharma-b0ab51403)
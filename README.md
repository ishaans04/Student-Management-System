# Student Management System

## Overview

Student Management System is a Java-based desktop application built using a client-server architecture. The application provides a Java Swing graphical user interface for managing student records and communicates with a Java server through socket programming.

The server handles client requests, processes student operations, and interacts with a MySQL database using JDBC. The project stores student data in a MySQL database named `studentdb`, with records maintained in the `students` table.

Screenshots and architecture images are stored separately inside the `docs/` folder. They are not embedded in this README.

## Features

- Add new student records
- View all student records
- Search student records
- Update existing student details
- Delete student records
- Client-server communication using Java sockets
- JDBC-based MySQL database connectivity
- MySQL Connector/J JAR files included in the `lib/` folder

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
|-- README.md
|-- docs/
|   |-- architecture/
|   |   |-- system-architecture.md
|   |   `-- system-architecture.svg
|   `-- screenshots/
|-- lib/
|   |-- mysql-connector-j-local.jar
|   `-- mysql-connector-j-9.7.0.jar
|-- src/
|   |-- client/
|   |   `-- StudentClient.java
|   |-- db/
|   |   `-- DBConnection.java
|   |-- model/
|   |   `-- Student.java
|   `-- server/
|       |-- ClientHandler.java
|       `-- StudentServer.java
`-- out/
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

## Future Improvements

- Add login and role-based access control
- Add input validation for all student fields
- Add better error handling and user-friendly messages
- Add search filters by ID, name, or branch
- Move database credentials to environment variables or a configuration file
- Add unit tests for server and database logic
- Add build tool support using Maven or Gradle
- Improve UI styling and layout responsiveness

## Author

Add your name and profile links here.

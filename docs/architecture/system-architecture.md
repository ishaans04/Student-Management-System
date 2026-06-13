# System Architecture

This document describes the high-level architecture of the Student Management System. The architecture diagram is safe for public documentation and intentionally excludes usernames, passwords, connection strings, API keys, and other sensitive configuration details.

## Architecture Overview

The application follows a client-server architecture built using Java Swing, Socket Programming, JDBC, and MySQL.

The user interacts with a desktop-based graphical interface developed using Java Swing. Client requests are transmitted through socket connections to the server layer, where business logic and CRUD operations are processed. Database interactions are handled through JDBC and persisted in a MySQL database.

## Component Responsibilities

| Component      | Responsibility                                                                     |
| -------------- | ---------------------------------------------------------------------------------- |
| Student User   | Interacts with the desktop application to manage student records.                  |
| StudentClient  | Provides the Java Swing graphical user interface and sends requests to the server. |
| StudentServer  | Accepts incoming socket connections and delegates client sessions.                 |
| ClientHandler  | Processes client requests and coordinates CRUD operations.                         |
| DBConnection   | Manages JDBC-based database connectivity.                                          |
| MySQL Database | Stores student records within the `studentdb` database.                            |

## Request Flow

1. The user performs an operation through the Java Swing interface.
2. `StudentClient` sends the request to the server via socket communication.
3. `StudentServer` accepts the connection and delegates processing to `ClientHandler`.
4. `ClientHandler` executes the requested business logic.
5. `DBConnection` performs the required database operation through JDBC.
6. The result is returned to the client and displayed in the user interface.

## Technology Stack

* Java
* Java Swing
* Socket Programming
* JDBC
* MySQL

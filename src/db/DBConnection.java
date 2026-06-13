package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
    private static final String USER = "YOUR_MYSQL_USERNAME";
    private static final String PASSWORD = "YOUR_PASSWORD"; 

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

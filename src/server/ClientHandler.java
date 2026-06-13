package server;

import db.DBConnection;
import java.io.*;
import java.net.Socket;
import java.sql.*;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Connection conn = DBConnection.getConnection()
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String[] parts = request.split(",");
                String command = parts[0].trim().toUpperCase();

                switch (command) {
                    case "ADD":
                        // ADD,id,name,branch,marks
                        PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO students VALUES (?, ?, ?, ?)");
                        ps.setInt(1, Integer.parseInt(parts[1].trim()));
                        ps.setString(2, parts[2].trim());
                        ps.setString(3, parts[3].trim());
                        ps.setDouble(4, Double.parseDouble(parts[4].trim()));
                        ps.executeUpdate();
                        out.println("Student added successfully.");
                        break;

                    case "VIEW":
                        // VIEW (returns all students)
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM students");
                        StringBuilder sb = new StringBuilder();
                        while (rs.next()) {
                            sb.append(rs.getInt("id")).append("|")
                              .append(rs.getString("name")).append("|")
                              .append(rs.getString("branch")).append("|")
                              .append(rs.getDouble("marks")).append(";");
                        }
                        out.println(sb.length() > 0 ? sb.toString() : "NO_RECORDS");
                        break;

                    case "DELETE":
                        // DELETE,id
                        PreparedStatement ds = conn.prepareStatement(
                            "DELETE FROM students WHERE id=?");
                        ds.setInt(1, Integer.parseInt(parts[1].trim()));
                        int rows = ds.executeUpdate();
                        out.println(rows > 0 ? "Student deleted." : "Student not found.");
                        break;

                    case "UPDATE":
                        // UPDATE,id,name,branch,marks
                        PreparedStatement us = conn.prepareStatement(
                            "UPDATE students SET name=?, branch=?, marks=? WHERE id=?");
                        us.setString(1, parts[2].trim());
                        us.setString(2, parts[3].trim());
                        us.setDouble(3, Double.parseDouble(parts[4].trim()));
                        us.setInt(4, Integer.parseInt(parts[1].trim()));
                        int updated = us.executeUpdate();
                        out.println(updated > 0 ? "Student updated." : "Student not found.");
                        break;

                    default:
                        out.println("Unknown command.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
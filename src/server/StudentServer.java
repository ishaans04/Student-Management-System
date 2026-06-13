package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5); // Thread pool for multithreading
        System.out.println("Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                pool.execute(new ClientHandler(clientSocket)); // Hand off to thread
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class StudentClient extends JFrame {

    private JTextField idField, nameField, branchField, marksField;
    private JTextArea resultArea;
    private JTable table;
    private DefaultTableModel tableModel;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public StudentClient() {
        connectToServer();
        buildUI();
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot connect to server! Start the server first.");
        }
    }

    private boolean ensureConnected() {
        if (out != null && in != null && socket != null && socket.isConnected() && !socket.isClosed()) {
            return true;
        }

        connectToServer();
        if (out != null && in != null && socket != null && socket.isConnected() && !socket.isClosed()) {
            return true;
        }

        SwingUtilities.invokeLater(() -> resultArea.setText("Error: Cannot connect to server. Start StudentServer first."));
        return false;
    }

    private void buildUI() {
        setTitle("Student Management System");
        setSize(750, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Branch:"));
        branchField = new JTextField();
        inputPanel.add(branchField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton deleteBtn = new JButton("Delete");
        JButton updateBtn = new JButton("Update");
        JButton clearBtn = new JButton("Clear");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(clearBtn);

        // --- Table ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Branch", "Marks"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Records"));

        // --- Status Area ---
        resultArea = new JTextArea(3, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Status"));

        // --- Layout Assembly ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // --- Button Actions (using background threads for responsive UI) ---
        addBtn.addActionListener(e -> new Thread(() -> addStudent()).start());
        viewBtn.addActionListener(e -> new Thread(() -> viewStudents()).start());
        deleteBtn.addActionListener(e -> new Thread(() -> deleteStudent()).start());
        updateBtn.addActionListener(e -> new Thread(() -> updateStudent()).start());
        clearBtn.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void addStudent() {
        try {
            if (!ensureConnected()) {
                return;
            }
            String request = "ADD," + idField.getText() + "," + nameField.getText()
                    + "," + branchField.getText() + "," + marksField.getText();
            out.println(request);
            String response = in.readLine();
            SwingUtilities.invokeLater(() -> resultArea.setText(response));
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> resultArea.setText("Error: " + e.getMessage()));
        }
    }

    private void viewStudents() {
        try {
            if (!ensureConnected()) {
                return;
            }
            out.println("VIEW");
            String response = in.readLine();
            System.out.println("Response = " + response);
            SwingUtilities.invokeLater(() -> {
                tableModel.setRowCount(0);
                if ("NO_RECORDS".equals(response)) {
                    resultArea.setText("No records found.");
                    return;
}
                for (String record : response.split(";")) {
                    String[] parts = record.split("\\|");
                    tableModel.addRow(parts);
                }
                resultArea.setText("Records loaded.");
            });
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> resultArea.setText("Error: " + e.getMessage()));
        }
    }

    private void deleteStudent() {
        try {
            if (!ensureConnected()) {
                return;
            }
            out.println("DELETE," + idField.getText());
            String response = in.readLine();
            SwingUtilities.invokeLater(() -> resultArea.setText(response));
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> resultArea.setText("Error: " + e.getMessage()));
        }
    }

    private void updateStudent() {
        try {
            if (!ensureConnected()) {
                return;
            }
            String request = "UPDATE," + idField.getText() + "," + nameField.getText()
                    + "," + branchField.getText() + "," + marksField.getText();
            out.println(request);
            String response = in.readLine();
            SwingUtilities.invokeLater(() -> resultArea.setText(response));
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> resultArea.setText("Error: " + e.getMessage()));
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        branchField.setText("");
        marksField.setText("");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentClient());
    }
}

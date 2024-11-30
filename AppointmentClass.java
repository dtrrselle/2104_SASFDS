package appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JTextField;

public class AppointmentClass {

    private String customerName;
    private String email;
    private String gender;

    // Constructor
    public AppointmentClass(String customerName, String email, String gender) {
        this.customerName = customerName;
        this.email = email;
        this.gender = gender;
    }

    AppointmentClass(JTextField cname, String emailAddress, String gender) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Method to establish a database connection
    private static Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/salonandspa";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the MYSQL database!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database. Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Insert appointment into the database
    public boolean insertAppointment() {
        String sql = "INSERT INTO appointments (customer_name, email, gender) VALUES (?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            if (conn != null) {
                pst.setString(1, this.customerName);
                pst.setString(2, this.email);
                pst.setString(3, this.gender);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Appointment added successfully! Data: [Customer Name: " + this.customerName + ", Email: " + this.email + ", Gender: " + this.gender + "]");
                    return true;
                } else {
                    System.out.println("Failed to add appointment.");
                    return false;
                }
            } else {
                System.out.println("No connection Available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Test input values (in a real application, you'd gather this data from a UI)
        String customerName = "John Doe";
        String email = "john.doe@example.com";
        String gender = "Male";

        // Create an instance of AppointmentClass
        AppointmentClass appointment = new AppointmentClass(customerName, email, gender);

        // Insert the appointment into the database
        boolean result = appointment.insertAppointment();

        // Print result of insertion
        if (result) {
            System.out.println("Appointment successfully added to the database.");
        } else {
            System.out.println("There was an issue adding the appointment.");
        }
    }
}

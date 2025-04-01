import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDB {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected successfully!");

            // Create a statement
            Statement stmt = conn.createStatement();
            
            // Execute query
            String sql = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            System.out.println("\nEmployee Records:");
            System.out.println("----------------");
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                
                System.out.printf("Employee ID: %d, Name: %s, Salary: %.2f%n", 
                                empId, name, salary);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("\nConnection closed successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
} 

import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/ProductDB";
    private static final String USER = "root";  // Change as needed
    private static final String PASSWORD = "";  // Change as needed

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n--- Product Management ---");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1 -> addProduct(conn, scanner);
                    case 2 -> viewProducts(conn);
                    case 3 -> updateProduct(conn, scanner);
                    case 4 -> deleteProduct(conn, scanner);
                    case 5 -> exit = true;
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        
        String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
            conn.commit();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction failed! Rolling back.");
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void viewProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nProduct List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Price: %.2f, Quantity: %d%n",
                        rs.getInt("ProductID"), rs.getString("ProductName"),
                        rs.getDouble("Price"), rs.getInt("Quantity"));
            }
        }
    }

    private static void updateProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter New Quantity: ");
        int quantity = scanner.nextInt();

        String sql = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Product updated successfully!");
        }
    }

    private static void deleteProduct(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        }
    }
}

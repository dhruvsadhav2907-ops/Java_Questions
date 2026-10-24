import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseCRUDDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/store_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL database successfully!\n");
            insertProduct(conn, "Mechanical Keyboard", 25, 2499.50);
            insertProduct(conn, "Wireless Mouse", 50, 799.00);
            System.out.println("\n--- Records After Insert ---");
            displayProducts(conn);
            updateProductPrice(conn, "Mechanical Keyboard", 2299.00);
            System.out.println("\n--- Records After Update ---");
            displayProducts(conn);
            deleteProduct(conn, "Wireless Mouse");
            System.out.println("\n--- Records After Delete ---");
            displayProducts(conn);
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void insertProduct(Connection conn, String name, int quantity, double price) throws SQLException {
        String insertSQL = "INSERT INTO products (product_name, quantity, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, quantity);
            pstmt.setDouble(3, price);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("[INSERT] Added product: " + name);
            }
        }
    }
    private static void updateProductPrice(Connection conn, String name, double newPrice) throws SQLException {
        String updateSQL = "UPDATE products SET price = ? WHERE product_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, name);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("[UPDATE] Updated price for '" + name + "' to ₹" + newPrice);
            } else {
                System.out.println("[UPDATE] No record found to update for: " + name);
            }
        }
    }
    private static void deleteProduct(Connection conn, String name) throws SQLException {
        String deleteSQL = "DELETE FROM products WHERE product_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, name);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("[DELETE] Removed product: " + name);
            } else {
                System.out.println("[DELETE] No record found to delete for: " + name);
            }
        }
    }
    private static void displayProducts(Connection conn) throws SQLException {
        String selectSQL = "SELECT product_id, product_name, quantity, price FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            System.out.printf("%-10s | %-25s | %-10s | %-12s%n", "ID", "Name", "Quantity", "Price (₹)");
            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                System.out.printf("%-10d | %-25s | %-10d | %-12.2f%n",
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"));
            }
            if (!hasRows) {
                System.out.println("(Table is currently empty)");
            }
        }
    }
}
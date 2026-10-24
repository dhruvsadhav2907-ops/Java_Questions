import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ProductsDisplayDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/store_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        String selectQuery = "SELECT product_id, product_name, quantity, price FROM products";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery)
        ) {
            System.out.println("Connected to MySQL database successfully!\n");
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf("%-12s | %-25s | %-10s | %-12s%n", "Product ID", "Product Name", "Quantity", "Price (₹)");
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                System.out.printf("%-12d | %-25s | %-10d | %-12.2f%n", id, name, quantity, price);
            }
            if (!hasRecords) {
                System.out.println("No product records found in the database.");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
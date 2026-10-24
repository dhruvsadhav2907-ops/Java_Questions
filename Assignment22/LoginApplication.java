import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class LoginApplication {
    private static final String URL = "jdbc:mysql://localhost:3306/auth_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("               SYSTEM LOGIN PORTAL                ");
        System.out.println("==================================================");
        System.out.print("Enter Username: ");
        String inputUsername = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String inputPassword = scanner.nextLine().trim();
        authenticateUser(inputUsername, inputPassword);
        scanner.close();
    }
    private static void authenticateUser(String username, String password) {
        String query = "SELECT full_name FROM users WHERE username = ? AND password = ?";
        try (
            Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("[SUCCESS] Authentication successful!");
                    System.out.println("Welcome back, " + fullName + "!");
                    System.out.println("--------------------------------------------------");
                } else {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("[FAILED] Invalid username or password.");
                    System.out.println("--------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
    }
}